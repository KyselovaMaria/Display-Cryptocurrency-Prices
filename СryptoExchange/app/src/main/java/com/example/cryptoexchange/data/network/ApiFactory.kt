package com.example.cryptoexchange.data.network

import android.util.Log
import com.example.cryptoexchange.presentation.MainActivity.Companion.TAG
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory(baseUrl: String) {
    private val retrofit: Retrofit
    val service: ApiService

    init {
        Log.d(TAG, "ApiFactory: Creating Retrofit instance with base URL: $baseUrl")
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)
    }
}
