package com.android_navigation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android_navigation.database.converter.ConversorBigDecimal
import com.android_navigation.database.dao.PagamentoDAO
import com.android_navigation.database.dao.ProdutoDAO
import com.android_navigation.model.Pagamento
import com.android_navigation.model.Produto

@Database(
    version = 1,
    entities = [Produto::class, Pagamento::class],
    exportSchema = false
)
@TypeConverters(ConversorBigDecimal::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDAO
    abstract fun pagamentoDao(): PagamentoDAO
}