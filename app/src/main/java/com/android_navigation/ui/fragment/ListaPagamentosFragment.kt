package com.android_navigation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android_navigation.R
import com.android_navigation.ui.recyclerview.adapter.ListaPagamentosAdapter
import com.android_navigation.viewmodel.ComponentesVisuais
import com.android_navigation.viewmodel.EstadoAppViewModel
import com.android_navigation.viewmodel.PagamentoViewModel
import kotlinx.android.synthetic.main.lista_pagamentos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListaPagamentosFragment : BaseFragment() {

    private val adapter: ListaPagamentosAdapter by inject()
    private val viewModel: PagamentoViewModel by viewModel()
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.lista_pagamentos, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista_pagamentos_recyclerview.adapter = adapter
        viewModel.todos().observe(this, Observer {
            it?.let { pagamentosEncontrados ->
                adapter.add(pagamentosEncontrados)
            }
        })
        estadoAppViewModel.temComponentes = ComponentesVisuais(
            appBar = true,
            bottomNavigation = true
        )
    }
}