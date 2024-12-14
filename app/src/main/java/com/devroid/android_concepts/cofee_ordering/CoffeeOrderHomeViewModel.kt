package com.devroid.android_concepts.cofee_ordering

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoffeeOrderHomeViewModel : ViewModel() {

    //UI DATA ------------------------>
    var coffee1Quantity = 0
    var coffee1Amount = 200

    var coffee2Quantity = 0
    var coffee2Amount = 250


    //Live data
    val grandTotalLiveData : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }


    //UI DATA <------------------------



    // UI DATA OPERATIONS ( BUSINESS LOGIC)
    fun incrementCoffee1(){
        coffee1Quantity += 1
    }

    fun decrementCoffee1(){
        coffee1Quantity -= 1
    }

    fun incrementCoffee2(){
        coffee2Quantity += 1
    }

    fun decrementCoffee2(){
        coffee2Quantity -= 1
    }

    //calculates grand total
    fun calculateGrandTotal(){
        val c1Total = coffee1Amount * coffee1Quantity
        val c2Total = coffee2Amount * coffee2Quantity
        grandTotalLiveData.value =  c1Total + c2Total
    }


}