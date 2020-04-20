package com.android_navigation.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.android_navigation.R
import com.android_navigation.ui.recyclerview.adapter.ProdutosAdapter
import com.android_navigation.viewmodel.LoginViewModel
import com.android_navigation.viewmodel.ProdutosViewModel
import kotlinx.android.synthetic.main.lista_produtos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ListaProdutosFragment : Fragment() {

    private val viewModel: ProdutosViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private val adapter: ProdutosAdapter by inject()
    private val controller by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        buscaProdutos()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_lista_produtos, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_lista_produto_deslogar) {
            loginViewModel.deslogar()
            val directions =
                ListaProdutosFragmentDirections.actionListaProdutosToLogin()
            controller.navigate(directions)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun buscaProdutos() {
        viewModel.buscaTodos().observe(this, Observer { produtoEncontrados ->
            produtoEncontrados?.let {
                adapter.atualiza(it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_produtos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraReclyerView()
    }


    private fun configuraReclyerView() {
        val divisor = DividerItemDecoration(context, VERTICAL)
        lista_produtos_recyclerview.addItemDecoration(divisor)
        adapter.onItemClickListener = { produtoSelecionado ->
            val directions =
                ListaProdutosFragmentDirections.actionListaProdutosToDetalhesProduto(
                    produtoSelecionado.id
                )
            controller.navigate(directions)

        }
        lista_produtos_recyclerview.adapter = adapter
    }
}
