package com.android_navigation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.android_navigation.R
import com.android_navigation.ui.recyclerview.adapter.ProdutosAdapter
import com.android_navigation.viewmodel.ComponentesVisuais
import com.android_navigation.viewmodel.EstadoAppViewModel
import com.android_navigation.viewmodel.ProdutosViewModel
import kotlinx.android.synthetic.main.lista_produtos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListaProdutosFragment : BaseFragment() {

    private val viewModel: ProdutosViewModel by viewModel()
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()
    private val adapter: ProdutosAdapter by inject()
    private val controller by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscaProdutos()
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
        hideKeyboard(activity)
        estadoAppViewModel.temComponentes = ComponentesVisuais(
            appBar = true,
            bottomNavigation = true
        )
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

    fun hideKeyboard(activity: FragmentActivity?) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Check if no view has focus
        val currentFocusedView = activity.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}
