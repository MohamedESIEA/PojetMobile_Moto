package com.example.moto.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moto.R
import java.util.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MotoListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MotoAdapter(listOf())
    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.moto_recyclerview)
        recyclerView.apply {
            layoutManager = this@MotoListFragment.layoutManager
            adapter = this@MotoListFragment.adapter
        }

        val motoList = arrayListOf<Moto>().apply {
            add(Moto("MT07"))
            add(Moto("Tracer700"))
            add(Moto("Tracer900"))
            add(Moto("KTM Adventure"))
        }

        adapter.updateList(motoList)


    }
}