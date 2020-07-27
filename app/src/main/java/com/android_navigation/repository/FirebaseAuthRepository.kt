package com.android_navigation.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

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

    fun cadastra(email: String, senha: String): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnSuccessListener {
            Log.i(TAG, "cadastra: cadastro sucedido")
            liveData.value = true
        }.addOnFailureListener {
            Log.e(TAG, "cadastra: cadastro falhou", it)
            liveData.value = false
        }
        return liveData
    }
}