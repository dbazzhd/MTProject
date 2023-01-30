package com.example.mtproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val m_country = MutableLiveData<Country>()
    val country: LiveData<Country>
        get() = m_country

    init {
        m_country.value = Country()
        Log.i("MainViewModel", "MainViewModel created!")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("MainViewModel", "MainViewModel destroyed!")
    }

    fun updateCountry(country: String) {
        REST.getCountry(country) {
            m_country.postValue(it)
        }
    }
}