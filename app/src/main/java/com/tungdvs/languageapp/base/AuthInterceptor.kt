package com.tungdvs.languageapp.base

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "apikey $apiKey")
            .build()
        return chain.proceed(request)
    }
}