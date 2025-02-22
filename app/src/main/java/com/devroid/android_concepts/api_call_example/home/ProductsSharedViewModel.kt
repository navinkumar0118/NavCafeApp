package com.devroid.android_concepts.api_call_example.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devroid.android_concepts.api_call_example.ApiBaseClient
import com.devroid.android_concepts.api_call_example.ApiBaseInterface
import com.devroid.android_concepts.api_call_example.ProductDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsSharedViewModel : ViewModel() {


    private lateinit var apiInterface: ApiBaseInterface


    //Live data - Products List
    val productsListLivedata: MutableLiveData<ArrayList<ProductDetail>> by lazy {
        MutableLiveData<ArrayList<ProductDetail>>()
    }

    //Live data - Carts List
    val cartLiveData: MutableLiveData<ArrayList<ProductDetail>> by lazy {
        MutableLiveData<ArrayList<ProductDetail>>()
    }

    //product list raw
    var productList : ArrayList<ProductDetail> = ArrayList()


    private val _totalPriceLiveData = MutableLiveData<Double>()
    val totalPriceLiveData: MutableLiveData<Double> get() = _totalPriceLiveData


    //API base object creation
    init {
        apiInterface = ApiBaseClient.getInstance().create(ApiBaseInterface::class.java)
        productsListLivedata.postValue(ArrayList<ProductDetail>())
        cartLiveData.postValue(ArrayList<ProductDetail>())
        totalPriceLiveData.postValue(0.0)
    }


    // Update product quantity
    fun updateProductQuantity(product: ProductDetail, quantity: Int) {

        val currentList = productsListLivedata.value!!
        var currentProduct = currentList.find { it.id == product.id }
        currentProduct?.quantity = quantity

        productsListLivedata.value = currentList

        // Update cart
        updateCart(product, quantity)
    }

    // Add or update cart items
    private fun updateCart(product: ProductDetail, quantity: Int) {

        val cartList = cartLiveData.value!!

        val existingProduct = cartList.find { it.id == product.id }

        if (quantity > 0) {
            if (existingProduct != null) {
                existingProduct.quantity = quantity
            } else {
                product.quantity = quantity
                cartList.add(product)
            }
        } else {
            cartList.remove(existingProduct)
        }

        cartLiveData.value = cartList
        calculateTotalPrice()
    }


    private fun calculateTotalPrice() {
        val total = cartLiveData.value?.sumOf { it.price * it.quantity } ?: 0.0
        _totalPriceLiveData.value = total
    }







    //api call function -> returns product list
    fun getProductsList() {

        apiInterface.getProductsListApi().enqueue(object : Callback<ArrayList<ProductDetail>> {

            override fun onResponse(
                call: Call<ArrayList<ProductDetail>>, response: Response<ArrayList<ProductDetail>>
            ) {
                apiInterface = ApiBaseClient.getInstance().create(ApiBaseInterface::class.java)
                if (response.isSuccessful && response.body() != null)
                {
                    /// success case
                    val list = response.body()
                    productList = list!!

                    productsListLivedata.postValue(list)
                    Log.d("API RESPONSE", list.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<ProductDetail>>, t: Throwable) {

                //failure case
                t.printStackTrace()
                Log.d("API RESPONSE", t.toString())

            }


        })
    }

}