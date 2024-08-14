package com.tungdvs.languageapp.base

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.http.BridgeInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient(
    private val baseURL: String,
    private val timeOutInSecond: Long = 90,
    private val interceptor: List<Interceptor> = emptyList()
) {
    private val client = OkHttpClient.Builder()
        .connectTimeout(timeOutInSecond, TimeUnit.SECONDS)
        .readTimeout(timeOutInSecond, TimeUnit.SECONDS)
        .writeTimeout(timeOutInSecond, TimeUnit.SECONDS)
        .apply {
            interceptor.forEach {
                addInterceptor(it)
            }
        }
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>): T = retrofit.create(service)
}