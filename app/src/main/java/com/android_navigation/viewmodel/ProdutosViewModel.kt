package com.android_navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android_navigation.model.Produto
import com.android_navigation.repository.ProdutoRepository

class ProdutosViewModel(private val repository: ProdutoRepository) : ViewModel() {

    fun buscaTodos(): LiveData<List<Produto>> = repository.buscaTodos()
}
