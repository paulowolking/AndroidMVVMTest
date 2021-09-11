package br.com.wolking.moneyinvestmvvm.di

import android.content.Context
import br.com.wolking.moneyinvestmvvm.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.module

object Module {

    private val viewModelModule = module {
        viewModel { HomeViewModel() }
    }

    lateinit var applicationContext: Context

    val koin: Koin? by lazy {
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(arrayListOf(viewModelModule))
        }.koin

    }
}