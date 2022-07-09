package com.example.myboxoffice.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitController {

    private fun client(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }


    private val retrofitController: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(APILinks.BASE_URL)
            .client(client())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: APIService by lazy {
        retrofitController
            .build()
            .create(APIService::class.java)
    }
}