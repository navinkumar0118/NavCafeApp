package com.devroid.android_concepts.api_call_example

import android.health.connect.datatypes.units.Length
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class ApiDemoHomePageViewModel : ViewModel() {
    // TODO: Implement the ViewModel


    private lateinit var apiInterface: ApiBaseInterface

    val responseList: MutableLiveData<ArrayList<ProductDetail>> by lazy {
        MutableLiveData<ArrayList<ProductDetail>>()
    }


    fun getProductsList() {


        apiInterface = ApiBaseClient.getInstance().create(ApiBaseInterface::class.java)

        val call = apiInterface.getProductsListApi()


        call.enqueue(object : Callback<ArrayList<ProductDetail>> {

            override fun onResponse(
                call: Call<ArrayList<ProductDetail>>, response: Response<ArrayList<ProductDetail>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    /// success case

                    val list = response.body()
                    responseList.postValue(list)
                    Log.d("API RESPONSE", list.toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<ProductDetail>>, t: Throwable) {
                t.printStackTrace()
                Log.d("API RESPONSE", t.toString())

            }


        })
    }

}