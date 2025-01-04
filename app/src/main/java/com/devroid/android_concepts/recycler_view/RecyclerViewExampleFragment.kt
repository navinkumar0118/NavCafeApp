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

  /*      val dataset = arrayOf("Navinkumar", "Suniil", "Solomon", "Austin", "Aswin","Navinkumar", "Suniil", "Solomon", "Austin", "Aswin","Navinkumar", "Suniil", "Solomon", "Austin", "Aswin")

        recyclerView = view.findViewById(R.id.myContactsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ContactsListAdapter(dataset)*/



        val list: ArrayList<Contact> = ArrayList()
        list.add(Contact("Navin", "7708551525",""))
        list.add(Contact("Solomon", "8y8y8y98",""))
        list.add(Contact("Aswin", "8t868686",""))
        list.add(Contact("Austin", "ss898",""))
        list.add(Contact("XYZ", "886868678",""))
        list.add(Contact("Navin", "7708551525",""))
        list.add(Contact("Solomon", "8y8y8y98",""))
        list.add(Contact("Aswin", "8t868686",""))
        list.add(Contact("Austin", "ss898",""))
        list.add(Contact("XYZ", "886868678",""))

        recyclerView = view.findViewById(R.id.myContactsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DemoListAdapter(list)




    }




}