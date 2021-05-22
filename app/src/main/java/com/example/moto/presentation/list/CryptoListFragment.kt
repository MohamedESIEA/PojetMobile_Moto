package com.example.moto.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
    private val viewModel : CryptoListViewModel by viewModels()
    private lateinit var loader : ProgressBar
    private lateinit var textViewError: TextView


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
        loader = view.findViewById(R.id.crypto_loader)
        textViewError = view.findViewById(R.id.crypto_error)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CryptoListFragment.adapter
        }

        viewModel.cryptoList.observe(viewLifecycleOwner, Observer {  cryptoModel ->
            loader.isVisible = cryptoModel is CryptoLoader
            textViewError.isVisible = cryptoModel is CryptoError

            if(cryptoModel is CryptoSuccess) {
                adapter.updateList(cryptoModel.cryptoList)
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

