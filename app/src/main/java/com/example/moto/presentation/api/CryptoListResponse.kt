package com.example.moto.presentation.api

import com.example.moto.presentation.list.Crypto

data class CryptoListResponse(
    val results: List<Crypto>
)