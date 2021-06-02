package com.example.data.di

import com.example.data.remote.MoviesRemoteApi
import com.example.data.repository.MoviesRepositoryImpl
import com.example.domain.repository.MoviesRepository
import org.koin.dsl.module


val repositoryModule = module {
    single { createMoviesRepository(get()) }
}

fun createMoviesRepository(remoteApi: MoviesRemoteApi): MoviesRepository {
    return MoviesRepositoryImpl(
        remoteApi
    )
}