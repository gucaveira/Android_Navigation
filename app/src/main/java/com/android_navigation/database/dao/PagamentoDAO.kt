package com.android_navigation.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.android_navigation.model.Pagamento

@Dao
interface PagamentoDAO {

    @Insert
    fun salva(pagamento: Pagamento): Long

}