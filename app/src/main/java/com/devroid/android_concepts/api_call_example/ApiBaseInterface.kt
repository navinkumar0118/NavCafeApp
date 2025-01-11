package com.devroid.android_concepts.api_call_example

import retrofit2.Call
import retrofit2.http.GET

interface ApiBaseInterface {

    @GET("products")
    fun getProductsListApi(): Call<ArrayList<ProductDetail>>

    @GET("products/categories")
    fun getCategoriesApi() : Call<ArrayList<String>>



}