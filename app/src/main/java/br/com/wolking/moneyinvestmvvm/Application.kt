package br.com.wolking.moneyinvestmvvm

import android.app.Application
import br.com.wolking.moneyinvestmvvm.di.Module

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        Module.applicationContext = applicationContext
        Module.koin
    }
}