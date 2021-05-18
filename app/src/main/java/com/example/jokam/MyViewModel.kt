package com.example.jokam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jokam.network.data.Country
import com.example.jokam.repos.RemoteRepository

class MyViewModel: ViewModel() {
    fun callAPI() : MutableLiveData<Country>
    {
        return RemoteRepository().callAPI()
    }
}