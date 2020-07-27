package com.android_navigation.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

private const val TAG = "FirebaseAuthRepository"

class FirebaseAuthRepository(private val firebaseAuth: FirebaseAuth) {

    private fun desloga(instanceFirebase: FirebaseAuth) {
        instanceFirebase.signOut()
    }

    private fun verificaUsuario(instanceFirebase: FirebaseAuth) {
        val currentUser = instanceFirebase.currentUser
        if (currentUser != null) {
        } else {
        }
    }

    private fun autenticaUsuario(instanceFirebase: FirebaseAuth) {
        instanceFirebase.signInWithEmailAndPassword(
            "gustavo.pereira.rocha@lojaesporte.com",
            "123456"
        ).addOnSuccessListener {
        }.addOnFailureListener {

        }
    }

    fun cadastra(email: String, senha: String): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnSuccessListener {
                Log.i(TAG, "cadastra: cadastro sucedido")
                liveData.value = Resource(true)
            }.addOnFailureListener { exception ->
                Log.e(TAG, "cadastra: cadastro falhou", exception)
                val mensagemError: String = when (exception) {
                    is FirebaseAuthWeakPasswordException -> "Senha precisa de pelo menos 6 digitos"
                    is FirebaseAuthInvalidCredentialsException -> "E-mail inválido"
                    is FirebaseAuthUserCollisionException -> "E-mail já cadastrado"
                    else -> "Erro Desconhecido"
                }
                liveData.value = Resource(false, mensagemError)
            }
        } catch (e: IllegalArgumentException) {
            liveData.value = Resource(false, "E-mail ou senha não podem ser vazio")
        }
        return liveData
    }
}