package com.example.mtproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OverviewViewModel(application: Application) : AndroidViewModel(application) {
    val countriesNONDATABASE = mutableListOf<Country>()

    //lateinit var adapter: RecyclerViewAdapter

    fun refreshDataFromRepository(callback: (adapter: RecyclerViewAdapter) -> Unit) {
        viewModelScope.launch {
            try {
                //m_countriesRepository.refreshCountries()
                countriesNONDATABASE.clear()
                val list = API.retrofitService.getCountries().toCountries()
                for (item in list) {
                    countriesNONDATABASE.add(item)
                }
                println(countriesNONDATABASE?.size)
                callback(RecyclerViewAdapter(countriesNONDATABASE))
                Log.d("OverviewActivity", "Success")
            } catch(exception: Exception) {
                Log.e("OverviewActivity", exception.message.toString())
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OverviewViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}