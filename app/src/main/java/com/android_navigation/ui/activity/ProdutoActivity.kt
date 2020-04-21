package com.android_navigation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.android_navigation.R

class MainActivity : AppCompatActivity() {

    private val controller by lazy {
        findNavController(R.id.main_activity_nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        controller.addOnDestinationChangedListener { controller, destination, arguments ->
            title = destination.label
            when (destination.id) {
                R.id.listaProdutos -> supportActionBar?.show()
                R.id.Login -> supportActionBar?.hide()
            }
        }
    }
}
