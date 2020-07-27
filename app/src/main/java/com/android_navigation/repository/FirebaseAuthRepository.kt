package com.android_navigation.repository

import android.util.Log
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

    fun cadastra(email: String, senha: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnSuccessListener {
            Log.i(TAG, "cadastra: cadastro sucedido")
        }.addOnFailureListener {
            Log.e(TAG, "cadastra: cadastro falhou", it)
        }
    }
}