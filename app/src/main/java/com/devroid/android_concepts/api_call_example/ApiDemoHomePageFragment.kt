package com.devroid.android_concepts.api_call_example

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devroid.android_concepts.livedata_example.LiveDataExampleViewModel
import com.devroid.devroidconcept.R
import com.devroid.devroidconcept.databinding.FragmentApiDemoHomePageBinding
import com.devroid.devroidconcept.databinding.FragmentLivedataExampleBinding

class ApiDemoHomePageFragment : Fragment() {

    companion object {
        fun newInstance() = ApiDemoHomePageFragment()
    }

    private lateinit var viewModel: ApiDemoHomePageViewModel
    private lateinit var _binding: FragmentApiDemoHomePageBinding
    private val binding get() = _binding

    private lateinit var adapter: ProductListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentApiDemoHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ApiDemoHomePageViewModel::class.java]

        //api call
        viewModel.getProductsList()

        //recycler adapter
        adapter = ProductListAdapter(viewModel.productList)
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.productsRecyclerView.adapter = adapter



        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.responseListLivedata.observe(viewLifecycleOwner,  Observer<ArrayList<ProductDetail>>
        { latestList ->
            adapter.updateProduct(latestList)
        })



    }

}