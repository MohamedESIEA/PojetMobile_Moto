package com.example.moto.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {
    @GET("assets")
    fun getCryptoList(@Query("limit") limit: String): Call<CryptoResponse>




}