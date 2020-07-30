package com.android_navigation.viewmodel

import androidx.lifecycle.ViewModel
import com.android_navigation.repository.FirebaseAuthRepository

class MinhaContaViewModel(val repository: FirebaseAuthRepository) : ViewModel() {

   val usuario= repository.getUsuario()

}