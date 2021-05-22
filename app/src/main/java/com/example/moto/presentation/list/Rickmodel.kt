package com.example.moto.presentation.list

sealed class RickModel

data class RickSuccess(val rickList : List<Rick>) : RickModel()
object RickLoader : RickModel()
object RickError : RickModel()