package com.example.jokam.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jokam.interfaces.RetrofitApiService
import com.example.jokam.network.data.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    fun callAPI(): MutableLiveData<Country>{
        val mutableLiveData = MutableLiveData<Country>()

        RetrofitApiService().fetchData().enqueue(object : Callback<Country>{
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                if(response.isSuccessful){
                    mutableLiveData.postValue(response.body())
                }
                else{
                    Log.d("Error","Could not Get the Data")
                }
            }

            override fun onFailure(call: Call<Country>, t: Throwable) {
                Log.d("Error","Could not Get the Data")
            }
        })
        return mutableLiveData
    }
}