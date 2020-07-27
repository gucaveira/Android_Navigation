package com.android_navigation.viewmodel

import androidx.lifecycle.ViewModel
import com.android_navigation.repository.FirebaseAuthRepository

class CadastroUsuarioViewModel(private val repository: FirebaseAuthRepository) : ViewModel() {

    fun cadrastra(email: String, senha: String) {
        repository.cadastra(email, senha)
    }
}