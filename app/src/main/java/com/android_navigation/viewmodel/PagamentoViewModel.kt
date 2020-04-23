package com.android_navigation.viewmodel

import androidx.lifecycle.ViewModel
import com.android_navigation.model.Pagamento
import com.android_navigation.repository.PagamentoRepository
import com.android_navigation.repository.ProdutoRepository

class PagamentoViewModel(
    private val pagamentoRepository: PagamentoRepository,
    private val produtodRepository: ProdutoRepository
) : ViewModel() {

    fun salva(pagamento: Pagamento) = pagamentoRepository.salva(pagamento)
    fun buscaProdutoPorId(id: Long) = produtodRepository.buscaPorId(id)
    fun todos() = pagamentoRepository.todos()
}
