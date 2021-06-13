package com.example.jokam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jokam.network.data.Country
import com.example.jokam.network.data.Covid19Countries
import com.example.jokam.network.data.WorldResponseItem
import com.example.jokam.repos.RemoteRepository

class MyViewModel: ViewModel() {
    val searchCountryLiveData = MutableLiveData<List<Covid19Countries>>()
    fun callAPI() : MutableLiveData<List<WorldResponseItem>>
    {
        return RemoteRepository().callAPI()
    }

    fun callAPIByCountry(query :String) : MutableLiveData<Country>
    {

        return RemoteRepository().fetchMovieByQuery(query)
    }


}