package com.devroid.android_concepts.livedata_example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataExampleViewModel : ViewModel() {


   val scoreLiveData : MutableLiveData<String> by lazy{
       MutableLiveData<String>()
   }


    init{
        scoreLiveData.value = "0.0"
    }


    fun updateLivedata(score : String ){
        scoreLiveData.value = score
    }

}