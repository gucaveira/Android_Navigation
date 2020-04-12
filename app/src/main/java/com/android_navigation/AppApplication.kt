package com.android_navigation

import android.app.Application
import com.android_navigation.di.daoModule
import com.android_navigation.di.testeDatabaseModule
import com.android_navigation.di.uiModule
import com.android_navigation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    testeDatabaseModule,
                    daoModule,
                    uiModule,
                    viewModelModule
                )
            )
        }
    }
}