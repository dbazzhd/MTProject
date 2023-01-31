package com.example.mtproject


import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val m_countriesRepository = CountriesRepository(getDatabase(application))

    private val m_countries = m_countriesRepository.countries


    private val m_countriesNONDATABASE = mutableListOf<Country>()

    lateinit var asa: String

    val country = MutableLiveData<Country>()


    private var m_eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = m_eventNetworkError

    private var m_isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = m_isNetworkErrorShown


    override fun onCleared() {
        super.onCleared()
        Log.i("MainViewModel", "MainViewModel destroyed!")
    }

    fun updateCountry(countryName: String) {
        if (!countryName.isNullOrEmpty() && !countryName.isNullOrBlank()) {
            val name: String = countryName.lowercase()
            /*for (item in m_countries.value!!){
                if (item.Name.lowercase() == name) {
                    country.value = item
                    break
                }
            }*/

            for (item in m_countriesNONDATABASE){
                if (item.Name.lowercase() == name) {
                    country.value = item
                    break
                }
            }
        }
    }

    fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                //m_countriesRepository.refreshCountries()
                m_countriesNONDATABASE.clear()
                val list = API.retrofitService.getCountries().toDatabaseCountries().toCountries()
                for (item in list) {
                    m_countriesNONDATABASE.add(item)
                }
                println(m_countriesNONDATABASE?.size)
            } catch(exception: Exception) {
                Log.i("API ERROR", exception.message.toString())
            }
        }
    }

    fun onNetworkErrorShown() {
        m_isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
