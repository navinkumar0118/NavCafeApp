package com.devroid.android_concepts.api_call_example.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devroid.android_concepts.api_call_example.ProductDetail
import com.devroid.devroidconcept.R
import com.devroid.devroidconcept.databinding.FragmentApiDemoHomePageBinding

class ApiDemoHomePageFragment : Fragment(), ProductListAdapter.ProductsCallBack {

    companion object {
        fun newInstance() = ApiDemoHomePageFragment()
    }

    private lateinit var viewModel: ProductsSharedViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProductsSharedViewModel::class.java]
        //api call
        viewModel.getProductsList()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recycler adapter
        adapter = ProductListAdapter(
            viewModel.productsListLivedata.value ?: ArrayList<ProductDetail>(),
            this
        )
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.productsRecyclerView.adapter = adapter


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.productsListLivedata.observe(viewLifecycleOwner,
            Observer<ArrayList<ProductDetail>>
            { latestList ->
                adapter.updateProduct(latestList)
            })


        binding.checkoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_apiDemoHomePageFragment_to_cartPageFragment)
        }


    }

    override fun onIncrementPressed(productDetail: ProductDetail) {
        val newQuantity = productDetail.quantity + 1
        viewModel.updateProductQuantity(productDetail, newQuantity)
    }

    override fun onDecrementPressed(productDetail: ProductDetail) {
        val newQuantity = (productDetail.quantity - 1).coerceAtLeast(0)
        viewModel.updateProductQuantity(productDetail, newQuantity)
    }

}