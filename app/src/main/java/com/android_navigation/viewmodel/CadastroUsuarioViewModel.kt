package com.android_navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android_navigation.repository.FirebaseAuthRepository
import com.android_navigation.repository.Resource

class CadastroUsuarioViewModel(private val repository: FirebaseAuthRepository) : ViewModel() {

    fun cadrastra(email: String, senha: String): LiveData<Resource<Boolean>> {
        return repository.cadastra(email, senha)
    }
}