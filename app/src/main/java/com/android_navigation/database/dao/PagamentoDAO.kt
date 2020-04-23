package com.android_navigation.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android_navigation.model.Pagamento

@Dao
interface PagamentoDAO {

    @Insert
    fun salva(pagamento: Pagamento): Long

    @Query("SELECT * FROM  pagamento")
    fun todo(): LiveData<List<Pagamento>>
}