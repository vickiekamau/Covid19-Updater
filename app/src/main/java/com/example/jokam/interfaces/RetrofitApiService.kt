package com.example.jokam.interfaces


import com.example.jokam.network.data.Country
import com.example.jokam.network.data.WorldResponseItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApiService {
    //https://corona.lmao.ninja/v2/countries
   //@GET("summary")
   // @GET("countries")
    //fun fetchData(): Call<Country>

    @GET("v1")
    fun fetchData(): Call<List<WorldResponseItem>>

    @GET("v1/{country}")
    fun fetchCountryByQueryAsync(@Path("country") country: String): Call<Country>

    companion object{
        operator fun invoke() : RetrofitApiService {
            return Retrofit.Builder()
                //.baseUrl("https://api.covid19api.com/")
                //.baseUrl("https://corona.lmao.ninja/v2/")
                .baseUrl("https://covid-19.dataflowkit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApiService::class.java)
        }
    }
}