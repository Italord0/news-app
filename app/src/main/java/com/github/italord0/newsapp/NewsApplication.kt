package com.github.italord0.newsapp

import android.app.Application
import com.github.italord0.newsapp.di.networkModule
import com.github.italord0.newsapp.di.repositoryModule
import com.github.italord0.newsapp.di.useCaseModule
import com.github.italord0.newsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApplication)
            modules(
                viewModelModule,
                repositoryModule,
                useCaseModule,
                networkModule
            )
        }
    }

}