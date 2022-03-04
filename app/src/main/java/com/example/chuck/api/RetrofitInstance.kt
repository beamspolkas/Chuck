package com.example.chuck.api

import android.util.Base64
import com.example.chuck.util.Constants.Companion.BASE_URL
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }

}