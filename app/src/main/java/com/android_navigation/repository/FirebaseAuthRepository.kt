package com.android_navigation.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_navigation.model.Usuario
import com.google.firebase.auth.*

private const val TAG = "FirebaseAuthRepository"

class FirebaseAuthRepository(private val firebaseAuth: FirebaseAuth) {

    fun cadastra(usuario: Usuario): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        try {
            firebaseAuth.createUserWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnSuccessListener {
                    Log.i(TAG, "cadastra: cadastro sucedido")
                    liveData.value = Resource(true)
                }.addOnFailureListener { exception ->
                    Log.e(TAG, "cadastra: cadastro falhou", exception)
                    val mensagemError: String = devolverErroCadastro(exception)
                    liveData.value = Resource(false, mensagemError)
                }
        } catch (e: IllegalArgumentException) {
            liveData.value = Resource(false, "E-mail ou senha não podem ser vazio")
        }
        return liveData
    }

    private fun devolverErroCadastro(exception: Exception): String {
        return when (exception) {
            is FirebaseAuthWeakPasswordException -> "Senha precisa de pelo menos 6 digitos"
            is FirebaseAuthInvalidCredentialsException -> "E-mail inválido"
            is FirebaseAuthUserCollisionException -> "E-mail já cadastrado"
            else -> "Erro Desconhecido"
        }
    }

    fun estaLogado(): Boolean {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            return true
        }
        return false
    }

    fun deslogar() {
        firebaseAuth.signOut()
    }

    fun autentica(usuario: Usuario): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        try {
            firebaseAuth.signInWithEmailAndPassword(usuario.email, usuario.senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        liveData.value = Resource(true)
                    } else {
                        Log.e(TAG, "autentica: ", task.exception)
                        val mensagemErro: String = devolverErroAuntenticacao(task.exception)
                        liveData.value = Resource(dado = false, error = mensagemErro)
                    }
                }
        } catch (e: IllegalArgumentException) {
            liveData.value = Resource(dado = false, error = "E-mail e senha não pode ser vazio")
        }
        return liveData
    }

    private fun devolverErroAuntenticacao(exception: Exception?): String {
        return when (exception) {
            is FirebaseAuthInvalidUserException -> "E-mail inválido ou inexistente"
            is FirebaseAuthInvalidCredentialsException -> "E-mail ou Senha incorreta"
            else -> "Erro desconhecido"
        }
    }
}