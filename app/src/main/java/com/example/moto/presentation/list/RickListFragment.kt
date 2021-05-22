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


class RickListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = RickAdapter(listOf(), ::onClickedRick)
    private val viewModel : RickListViewModel by viewModels()
    private lateinit var loader : ProgressBar
    private lateinit var textViewError: TextView


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rick_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rick_recyclerview)
        loader = view.findViewById(R.id.rick_loader)
        textViewError = view.findViewById(R.id.rick_error)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@RickListFragment.adapter
        }

        viewModel.rickList.observe(viewLifecycleOwner, Observer { rickModel ->
            loader.isVisible = rickModel is RickLoader
            textViewError.isVisible = rickModel is RickError

            if(rickModel is RickSuccess) {
                adapter.updateList(rickModel.rickList)
            }

        })
    }


    private fun showList(rickList: List<Rick>) {
        if (rickList != null) {
            adapter.updateList(rickList)
        }
    }

    private fun onClickedRick(id: Int) {
        findNavController().navigate(R.id.navigateToRickDetailFragment, bundleOf(
            "rickId" to (id+1)
        ))
    }
}

