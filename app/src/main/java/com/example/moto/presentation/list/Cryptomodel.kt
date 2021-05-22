package com.example.moto.presentation.list

sealed class CryptoModel

data class CryptoSuccess(val cryptoList : List<Crypto>) : CryptoModel()
object CryptoLoader : CryptoModel()
object CryptoError : CryptoModel()