package com.example.moto.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickApi {
    @GET("character")
    fun getRickList(@Query("limit") limit: String): Call<RickListResponse>

    @GET("character/{id}")
    fun getRickDetail(@Path("id") id: Int): Call<RickDetailResponse>





}