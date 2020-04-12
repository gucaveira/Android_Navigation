package com.android_navigation.viewmodel

import androidx.lifecycle.ViewModel
import com.android_navigation.repository.ProdutoRepository

class DetalhesProdutoViewModel(
    produtoId: Long,
    repository: ProdutoRepository
) : ViewModel() {
    val produtoEncontrado = repository.buscaPorId(produtoId)
}
