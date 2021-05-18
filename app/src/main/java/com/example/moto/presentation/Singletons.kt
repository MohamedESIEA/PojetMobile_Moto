package com.example.moto.presentation

import com.example.moto.presentation.CryptoApplication.Companion.context
import com.example.moto.presentation.api.CryptoApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singletons {
    companion object {

        var cache = Cache(File(context?.cacheDir,"responses"), 10*1024*1024 )

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()

        val cryptoApi: CryptoApi = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CryptoApi::class.java)


    }
}



