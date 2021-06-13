package com.example.jokam.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jokam.interfaces.RetrofitApiService
import com.example.jokam.network.data.Country
import com.example.jokam.network.data.WorldResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RemoteRepository {

    fun callAPI(): MutableLiveData<List<WorldResponseItem>>{
        val mutableLiveData = MutableLiveData<List<WorldResponseItem>>()

        RetrofitApiService().fetchData().enqueue(object : Callback<List<WorldResponseItem>>{
            override fun onResponse(call: Call<List<WorldResponseItem>>, response: Response<List<WorldResponseItem>>) {
                if(response.isSuccessful){
                    mutableLiveData.postValue(response.body())

                }
                else{
                    Log.d("Error","Could not Get the Data 1")
                }
            }

            override fun onFailure(call: Call<List<WorldResponseItem>>, t: Throwable) {
                if (t is IOException) {
                    //Toast.makeText(Dashboard@this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    Log.d("Error","Network failure " )
                }
                else {
                    //Toast.makeText(Dashboard@this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                    Log.d("Error","Data conversion issue!" )
                }

            }
        })
        return mutableLiveData
    }

     fun fetchMovieByQuery(countryText: String): MutableLiveData<Country>{
         val mutableLiveData = MutableLiveData<Country>()

         RetrofitApiService().fetchCountryByQueryAsync(countryText).enqueue(object : Callback<Country>{
             override fun onResponse(call: Call<Country>, response: Response<Country>) {
                 if(response.isSuccessful){
                     mutableLiveData.postValue(response.body())

                 }
                 else{
                     Log.d("Error","Could not Get the Data 1")
                 }
             }

             override fun onFailure(call: Call<Country>, t: Throwable) {
                 if (t is IOException) {
                     //Toast.makeText(Dashboard@this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                     Log.d("Error","Network failure " )
                 }
                 else {
                     //Toast.makeText(Dashboard@this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                     // todo log to some central bug tracking service
                     Log.d("Error","Data conversion issue!" )
                 }

             }
         })
         return mutableLiveData
     }


}