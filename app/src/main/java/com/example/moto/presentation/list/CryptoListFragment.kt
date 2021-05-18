package com.example.moto.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moto.R
import com.example.moto.presentation.Singletons
import com.example.moto.presentation.api.CryptoListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CryptoListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = CryptoAdapter(listOf(), ::onClickedCrypto)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.crypto_recyclerview)


        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CryptoListFragment.adapter
        }


        Singletons.cryptoApi.getCryptoList("25").enqueue(object : Callback<CryptoListResponse>{
            override fun onResponse(call: Call<CryptoListResponse>, response: Response<CryptoListResponse>) {
                if (response.isSuccessful && response.body() != null){
                    val cryptoResponse = response.body()
                    if (cryptoResponse != null) {
                        adapter.updateList(cryptoResponse.results)
                    }
                }
            }

            override fun onFailure(call: Call<CryptoListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }


    private fun showList(cryptoList: List<Crypto>) {
        if (cryptoList != null) {
            adapter.updateList(cryptoList)
        }
    }

    private fun onClickedCrypto(id: Int) {
        findNavController().navigate(R.id.navigateToCryptoDetailFragment, bundleOf(
            "cryptoId" to (id+1)
        ))
    }
}

