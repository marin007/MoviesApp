package com.example.data.di

import android.content.Context
import com.example.data.BuildConfig
import com.example.data.R
import com.example.data.common.AuthInterceptor
import com.example.data.common.NoConnectionInterceptor
import com.example.data.remote.MoviesRemoteApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {

    single {
        MoshiConverterFactory.create(
            Moshi.Builder().add(
                KotlinJsonAdapterFactory()
            ).build()
        ) as Converter.Factory
    }
    single {
        OkHttpClient.Builder().apply {
            connectTimeout(60L, TimeUnit.SECONDS)
            readTimeout(60L, TimeUnit.SECONDS)
            addInterceptor(
                NoConnectionInterceptor(
                    androidContext()
                )
            )
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addInterceptor(AuthInterceptor())

        }.build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .addConverterFactory(get())
            .build()
    }
    single { get<Retrofit>().create(MoviesRemoteApi::class.java) }
}