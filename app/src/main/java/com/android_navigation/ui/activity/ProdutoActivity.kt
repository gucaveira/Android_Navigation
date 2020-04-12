package com.android_navigation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android_navigation.R

private const val COMPRA_REALIZADA = "Compra realizada"

class ProdutosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.produtos_activity)
    }
}
