package com.android_navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android_navigation.model.Usuario
import com.android_navigation.repository.FirebaseAuthRepository
import com.android_navigation.repository.Resource

class LoginViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    fun autentica(usuario: Usuario): LiveData<Resource<Boolean>> {
        return firebaseAuthRepository.autentica(usuario)
    }

    fun estaLogado(): Boolean = firebaseAuthRepository.estaLogado()

    fun deslogar() {
        firebaseAuthRepository.deslogar()
    }

    fun naoEstaLogado(): Boolean = !estaLogado()
}
