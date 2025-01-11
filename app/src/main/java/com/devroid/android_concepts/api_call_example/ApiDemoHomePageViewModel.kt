package com.devroid.android_concepts.api_call_example

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiDemoHomePageViewModel : ViewModel() {
    // TODO: Implement the ViewModel


    private lateinit var apiInterface: ApiBaseInterface


    //Live data
    val responseListLivedata: MutableLiveData<ArrayList<ProductDetail>> by lazy {
        MutableLiveData<ArrayList<ProductDetail>>()
    }

    //product list raw
    var productList : ArrayList<ProductDetail> = ArrayList()


    //API base object creation
    init {
        apiInterface = ApiBaseClient.getInstance().create(ApiBaseInterface::class.java)

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

                    responseListLivedata.postValue(list)
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