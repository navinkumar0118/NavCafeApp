package com.devroid.android_concepts.api_call_example

import com.google.gson.annotations.SerializedName

class ProductDetail(
    val id : Int,
    val title : String,
    var price : Double,
    val description : String,
    val image : String,
    val rating : Rating,
    var quantity: Int = 0
)



