package com.example.mtproject

import android.R
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val m_blankCountry = Country()

    private val m_country = MutableLiveData<Country>()
    val country: LiveData<Country>
        get() = m_country

    private val m_countries = MutableLiveData<MutableList<Country>>()
    val countries: LiveData<MutableList<Country>>
        get() = m_countries

    init {
        m_country.value = Country()
        m_countries.value = MutableList(1) { m_blankCountry }
        Log.i("MainViewModel", "MainViewModel created!")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("MainViewModel", "MainViewModel destroyed!")
    }

    fun updateCountry(country: String) {
        if (!country.isNullOrEmpty() && !country.isNullOrBlank()) {
            var result: Country = m_blankCountry
            val name: String = country.lowercase()
            for (item in m_countries.value!!){
                if (item.Name.lowercase() == name) {
                    result = item
                    break
                }
            }
            m_country.value = result
        }
    }

    fun downloadCountries() {
        REST.getCountries {
            m_countries.postValue(it)
        }
    }
}