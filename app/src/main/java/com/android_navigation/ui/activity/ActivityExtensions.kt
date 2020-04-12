package com.android_navigation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.transacaoFragment(execute: FragmentTransaction.() -> Unit) {
    val transaction = supportFragmentManager.beginTransaction()
    execute(transaction)
    transaction.commit()
}