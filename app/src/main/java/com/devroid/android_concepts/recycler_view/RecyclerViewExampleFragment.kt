package com.devroid.android_concepts.recycler_view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devroid.devroidconcept.R

class RecyclerViewExampleFragment : Fragment() {



    private lateinit var viewModel: RecyclerViewExampleViewModel
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_example, container, false)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecyclerViewExampleViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataset = arrayOf("Navinkumar", "Suniil", "Solomon", "Austin", "Aswin","Navinkumar", "Suniil", "Solomon", "Austin", "Aswin","Navinkumar", "Suniil", "Solomon", "Austin", "Aswin")

        recyclerView = view.findViewById(R.id.myContactsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ContactsListAdapter(dataset)

    }




}