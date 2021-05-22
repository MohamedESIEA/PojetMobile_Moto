package com.example.moto.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moto.presentation.Singletons
import com.example.moto.presentation.api.RickListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RickListViewModel : ViewModel(){

    val rickList: MutableLiveData<RickModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        rickList.value = RickLoader

        Singletons.RICK_API.getRickList("25").enqueue(object : Callback<RickListResponse> {
            override fun onResponse(
                call: Call<RickListResponse>,
                response: Response<RickListResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val rickResponse = response.body()!!
                    rickList.value= RickSuccess(rickResponse.results)
                }else {
                    rickList.value= RickError
                }
            }

            override fun onFailure(call: Call<RickListResponse>, t: Throwable) {
                rickList.value= RickError
            }
        })
    }
}