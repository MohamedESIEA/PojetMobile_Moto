package com.example.moto.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moto.R
import com.example.moto.presentation.Singletons
import com.example.moto.presentation.api.RickDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RickDetailFragment : Fragment() {

    private lateinit var textViewGender: TextView
    private lateinit var textViewSpecies : TextView
    private lateinit var textViewStatus : TextView
    private lateinit var textViewName : TextView
    private lateinit var imageView: ImageView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rick_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewGender = view.findViewById(R.id.rick_detail_gender)
        textViewSpecies = view.findViewById(R.id.rick_detail_species)
        imageView = view.findViewById(R.id.rick_img)
        textViewStatus = view.findViewById(R.id.rick_detail_status)
        textViewName = view.findViewById(R.id.rick_detail_name)
        callApi()
    }

    private fun callApi() {
        val id = arguments?.getInt("rickId") ?: -1
        Singletons.RICK_API.getRickDetail(id).enqueue(object : Callback<RickDetailResponse>{
            override fun onResponse(call: Call<RickDetailResponse>, response: Response<RickDetailResponse>
            ) {
                if (response.isSuccessful && response.body() != null){
                    textViewGender.text = response.body()!!.gender
                    textViewSpecies.text = response.body()!!.species
                    textViewStatus.text = response.body()!!.status
                    textViewName.text = response.body()!!.name
                    Glide.with(this@RickDetailFragment).load(response.body()!!.image).into(imageView)


                }

            }

            override fun onFailure(call: Call<RickDetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

}