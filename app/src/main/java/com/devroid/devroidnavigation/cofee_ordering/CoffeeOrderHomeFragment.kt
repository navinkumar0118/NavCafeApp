package com.devroid.devroidnavigation.cofee_ordering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devroid.devroidnavigation.R
import com.devroid.devroidnavigation.databinding.FragmentCoffeeOrderHomeBinding


class CoffeeOrderHomeFragment : Fragment() {

    private lateinit var _binding: FragmentCoffeeOrderHomeBinding
    private val binding get() = _binding



    //view model declaration
    lateinit var viewModel: CoffeeOrderHomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCoffeeOrderHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model initialisation
        viewModel = ViewModelProvider(this)[CoffeeOrderHomeViewModel::class.java]

        updateLatestValues()

        _binding.c1.incrementCountButton.setOnClickListener {
            viewModel.incrementCoffee1()
            viewModel.calculateGrandTotal()
            updateLatestValues()
        }

        _binding.c1.decrementCountButtonw.setOnClickListener {
            if(viewModel.coffee1Quantity>0){
                viewModel.decrementCoffee1()
                viewModel.calculateGrandTotal()
                updateLatestValues()
            }

        }

        _binding.c2.incrementCountButton2.setOnClickListener {
            viewModel.incrementCoffee2()
            viewModel.calculateGrandTotal()
            updateLatestValues()
        }

        _binding.c2.decrementCountButton2.setOnClickListener {
            if(viewModel.coffee2Quantity>0) {
                viewModel.decrementCoffee2()
                viewModel.calculateGrandTotal()
                updateLatestValues()
            }
        }


        // Create the observer which updates the UI.
        val grandTotalListener = Observer<Int> { value ->
            _binding.grandTotal.text = value.toString()
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.grandTotalLiveData.observe(viewLifecycleOwner, grandTotalListener)

    }




    //this will function will update latest variable
    fun updateLatestValues() {
        _binding.c1.productAmount1.text = viewModel.coffee1Amount.toString()
        _binding.c1.quantityCount.text = viewModel.coffee1Quantity.toString()

        _binding.c2.productAmount2.text = viewModel.coffee2Amount.toString()
        _binding.c2.quantityCount2.text = viewModel.coffee2Quantity.toString()

    }


}