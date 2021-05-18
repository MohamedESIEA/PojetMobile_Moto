package com.example.moto.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.moto.R
import com.example.moto.presentation.Singletons
import com.example.moto.presentation.api.CryptoDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CryptoDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewSpecies : TextView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.crypto_detail_name)
        textViewSpecies = view.findViewById(R.id.crypto_detail_species)
        callApi()
    }

    private fun callApi() {
        val id = arguments?.getInt("cryptoId") ?: -1
        Singletons.cryptoApi.getCryptoDetail(id).enqueue(object : Callback<CryptoDetailResponse>{
            override fun onResponse(call: Call<CryptoDetailResponse>, response: Response<CryptoDetailResponse>
            ) {
                if (response.isSuccessful && response.body() != null){
                    textViewName.text = response.body()!!.gender
                    textViewSpecies.text = response.body()!!.species
                }

            }

            override fun onFailure(call: Call<CryptoDetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}