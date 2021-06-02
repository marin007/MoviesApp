package com.example.data.common

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val finalToken = "Bearer ${BuildConfig.TOKEN}"
        request = request.newBuilder()
            .addHeader("Authorization", finalToken)
            .build()
        return chain.proceed(request)
    }

}