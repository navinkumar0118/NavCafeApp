package com.devroid.android_concepts.api_call_example

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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
        viewModel.getProductsList()
        _binding.responseTextview.text = "LOADING DATA FROM API"

        // Create the observer which updates the UI.
        val responseList = Observer<ArrayList<ProductDetail>> { latestValue ->

           var resultString = ""

            for (products in latestValue)
            {
                resultString += "\n ${products.title}"
            }

            _binding.responseTextview.text = resultString
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.responseList.observe(viewLifecycleOwner, responseList)


    }

}