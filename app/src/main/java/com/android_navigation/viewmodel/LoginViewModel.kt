package com.android_navigation.viewmodel

import androidx.lifecycle.ViewModel
import com.android_navigation.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    fun loga() {
        repository.loga()
    }

    fun estaLogado(): Boolean = repository.estaLogado()

    fun deslogar() {
        repository.deslogar()
    }
}
