package com.android_navigation.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android_navigation.database.AppDatabase
import com.android_navigation.database.dao.PagamentoDAO
import com.android_navigation.database.dao.ProdutoDAO
import com.android_navigation.model.Produto
import com.android_navigation.repository.FirebaseAuthRepository
import com.android_navigation.repository.PagamentoRepository
import com.android_navigation.repository.ProdutoRepository
import com.android_navigation.ui.fragment.DetalhesProdutoFragment
import com.android_navigation.ui.fragment.ListaProdutosFragment
import com.android_navigation.ui.fragment.PagamentoFragment
import com.android_navigation.ui.recyclerview.adapter.ListaPagamentosAdapter
import com.android_navigation.ui.recyclerview.adapter.ProdutosAdapter
import com.android_navigation.viewmodel.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.math.BigDecimal

private const val NOME_BANCO_DE_DADOS = "lojaesporte.db"
private const val NOME_BANCO_DE_DADOS_TESTE = "lojaesporte-test.db"

val testeDatabaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS_TESTE
        ).fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(IO).launch {
                        val dao: ProdutoDAO by inject()
                        dao.salva(
                            Produto(
                                nome = "Bola de futebol",
                                preco = BigDecimal("100")
                            ), Produto(
                                nome = "Camisa",
                                preco = BigDecimal("80")
                            ),
                            Produto(
                                nome = "Chuteira",
                                preco = BigDecimal("120")
                            ), Produto(
                                nome = "Bermuda",
                                preco = BigDecimal("60")
                            )
                        )
                    }
                }
            }).build()
    }
}

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }
}

val daoModule = module {
    single<ProdutoDAO> { get<AppDatabase>().produtoDao() }
    single<PagamentoDAO> { get<AppDatabase>().pagamentoDao() }
    single<ProdutoRepository> { ProdutoRepository(get()) }
    single<PagamentoRepository> { PagamentoRepository(get()) }
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }
    single<FirebaseAuthRepository> { FirebaseAuthRepository(get()) }
}

val uiModule = module {
    factory<DetalhesProdutoFragment> { DetalhesProdutoFragment() }
    factory<ListaProdutosFragment> { ListaProdutosFragment() }
    factory<PagamentoFragment> { PagamentoFragment() }
    factory<ProdutosAdapter> { ProdutosAdapter(get()) }
    factory<ListaPagamentosAdapter> { ListaPagamentosAdapter(get()) }
}

val viewModelModule = module {
    viewModel<ProdutosViewModel> { ProdutosViewModel(get()) }
    viewModel<DetalhesProdutoViewModel> { (id: Long) -> DetalhesProdutoViewModel(id, get()) }
    viewModel<PagamentoViewModel> { PagamentoViewModel(get(), get()) }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<EstadoAppViewModel> { EstadoAppViewModel() }
    viewModel<CadastroUsuarioViewModel> { CadastroUsuarioViewModel(get()) }
}

val firebaseModule = module {
    single<FirebaseAuth> { Firebase.auth }
}