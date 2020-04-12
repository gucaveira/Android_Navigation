package com.android_navigation.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_navigation.database.dao.PagamentoDAO
import com.android_navigation.model.Pagamento
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PagamentoRepository(private val dao: PagamentoDAO) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun salva(pagamento: Pagamento): LiveData<Resource<Long>> {
        return MutableLiveData<Resource<Long>>().also { liveData ->
            scope.launch {
                val idpagamento = dao.salva(pagamento)
                liveData.postValue(Resource(idpagamento))
            }
        }
    }
}