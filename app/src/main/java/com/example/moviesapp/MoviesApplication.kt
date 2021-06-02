package com.example.moviesapp

import android.app.Application
import com.example.data.di.remoteModule
import com.example.data.di.repositoryModule
import com.example.moviesapp.di.useCasesModule
import com.example.moviesapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(listOf(
                remoteModule,
                repositoryModule,
                viewModelsModule,
                useCasesModule
            ))
        }
    }
}