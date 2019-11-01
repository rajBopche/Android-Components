package com.example.androidplayground.utility.network

import com.example.androidplayground.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var apiService: RestService? = null

    @Synchronized
    fun getClient(): RestService {

        if (apiService == null) {

            val logging = HttpLoggingInterceptor()
            logging.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient.Builder().run {
                addInterceptor(logging)
                connectTimeout(3, TimeUnit.MINUTES)
                readTimeout(3, TimeUnit.MINUTES)
                build()
            }

            val retrofit = Retrofit.Builder().run {
                baseUrl("https://api.github.com/users/")
                validateEagerly(BuildConfig.DEBUG)
                addConverterFactory(GsonConverterFactory.create())
                client(okHttpClient)
                build()
            }

            apiService = retrofit.create(RestService::class.java)
        }

        return apiService!!
    }
}