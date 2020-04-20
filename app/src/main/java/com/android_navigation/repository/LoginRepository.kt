package com.android_navigation.repository

import android.content.SharedPreferences
import androidx.core.content.edit

private const val CHAVE_LOGADO = "logado"

class LoginRepository(private val preferences: SharedPreferences) {

    fun loga() {
        preferences.edit {
            putBoolean(CHAVE_LOGADO, true)
        }
    }

    fun estaLogado(): Boolean = preferences.getBoolean(CHAVE_LOGADO, false)


}
