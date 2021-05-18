package com.example.moto.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {
    @GET("character")
    fun getCryptoList(@Query("limit") limit: String): Call<CryptoListResponse>

    @GET("character/{id}")
    fun getCryptoDetail(@Path("id") id: Int): Call<CryptoDetailResponse>





}