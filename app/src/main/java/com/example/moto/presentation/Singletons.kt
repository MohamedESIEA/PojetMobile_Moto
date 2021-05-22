package com.example.moto.presentation

import com.example.moto.presentation.RickApplication.Companion.context
import com.example.moto.presentation.api.RickApi
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

        val RICK_API: RickApi = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RickApi::class.java)


    }
}



