package com.android_navigation.ui.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(mensagem: String) {
    Snackbar.make(this, mensagem, Snackbar.LENGTH_LONG).show()
}