package com.simka.todolistsample

import android.app.Application
import com.simka.todolistsample.di.databaseModule
import com.simka.todolistsample.di.repositoryModule
import com.simka.todolistsample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule, databaseModule))
        }
    }
}