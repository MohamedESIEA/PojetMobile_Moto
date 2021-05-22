package com.example.moto.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moto.presentation.Singletons
import com.example.moto.presentation.api.CryptoListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoListViewModel : ViewModel(){

    val cryptoList: MutableLiveData<CryptoModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        cryptoList.value = CryptoLoader

        Singletons.cryptoApi.getCryptoList("25").enqueue(object : Callback<CryptoListResponse> {
            override fun onResponse(
                call: Call<CryptoListResponse>,
                response: Response<CryptoListResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val cryptoResponse = response.body()!!
                    cryptoList.value= CryptoSuccess(cryptoResponse.results)
                }else {
                    cryptoList.value= CryptoError
                }
            }

            override fun onFailure(call: Call<CryptoListResponse>, t: Throwable) {
                cryptoList.value= CryptoError
            }
        })
    }
}