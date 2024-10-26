package com.devroid.devroidnavigation.cofee_ordering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.devroid.devroidnavigation.R
import com.devroid.devroidnavigation.databinding.FragmentCoffeeOrderHomeBinding


class CoffeeOrderHomeFragment : Fragment() {

    private lateinit var _binding: FragmentCoffeeOrderHomeBinding
    private val binding get() = _binding


    var coffee1Quantity = 0
    var coffee1Amount = 200

    var coffee2Quantity = 0
    var coffee2Amount = 250
    var grandTotal = 0


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
        updateLatestValues()

        _binding.c1.incrementCountButton.setOnClickListener {
            coffee1Quantity += 1
            calculateGrandTotal()
            updateLatestValues()
        }

        _binding.c1.decrementCountButtonw.setOnClickListener {
            if(coffee1Quantity>0){
                coffee1Quantity -= 1
                calculateGrandTotal()
                updateLatestValues()
            }

        }

        _binding.c2.incrementCountButton2.setOnClickListener {
            coffee2Quantity += 1
            calculateGrandTotal()
            updateLatestValues()
        }

        _binding.c2.decrementCountButton2.setOnClickListener {
            if(coffee2Quantity>0) {
                coffee2Quantity -= 1
                calculateGrandTotal()
                updateLatestValues()
            }
        }
    }

   //calculates grand total
    fun calculateGrandTotal(){
        val c1Total = coffee1Amount * coffee1Quantity
        val c2Total = coffee2Amount * coffee2Quantity
       grandTotal = c1Total + c2Total
    }


    //this will function will update latest variable
    fun updateLatestValues() {
        _binding.c1.productAmount1.text = coffee1Amount.toString()
        _binding.c1.quantityCount.text = coffee1Quantity.toString()

        _binding.c2.productAmount2.text = coffee2Amount.toString()
        _binding.c2.quantityCount2.text = coffee2Quantity.toString()

        _binding.grandTotal.text = grandTotal.toString()
    }


}