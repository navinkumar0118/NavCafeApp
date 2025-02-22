package com.devroid.android_concepts.api_call_example.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devroid.android_concepts.api_call_example.ProductDetail
import com.devroid.android_concepts.api_call_example.home.ProductsSharedViewModel
import com.devroid.devroidconcept.databinding.FragmentCartPageBinding
import kotlin.math.roundToInt

class CartPageFragment : Fragment(), CartListAdapter.ProductsCallBack {

    companion object {
        fun newInstance() = CartPageFragment()
    }

    private lateinit var viewModel: ProductsSharedViewModel
    private lateinit var _binding: FragmentCartPageBinding
    private val binding get() = _binding

    private lateinit var adapter: CartListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ProductsSharedViewModel::class.java]



        //recycler adapter
        adapter = CartListAdapter(viewModel.cartLiveData.value as ArrayList<ProductDetail>, this)
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.cartRecyclerView.adapter = adapter


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.cartLiveData.observe(viewLifecycleOwner,
            Observer<ArrayList<ProductDetail>>
            { latestList ->
                adapter.updateCartList(latestList)
            })


        viewModel.totalPriceLiveData.observe(viewLifecycleOwner,
            Observer<Double>
            { latedtTotal ->
                binding.totalAmountTextView.text = "₹ ${latedtTotal.roundToInt()}"
            })

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