package com.android_navigation.repository

import androidx.lifecycle.LiveData
import com.android_navigation.database.dao.ProdutoDAO
import com.android_navigation.model.Produto

class ProdutoRepository(private val dao: ProdutoDAO) {

    fun buscaTodos(): LiveData<List<Produto>> = dao.buscaTodos()

    fun buscaPorId(id: Long): LiveData<Produto> = dao.buscaPorId(id)

}
