package com.example.mtproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.room.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepository(public val database: CountriesDatabase) {

    val countries: LiveData<List<Country>> = Transformations.map(database.countryDao.getCountries()) {
        it.toCountries()
    }

    suspend fun refreshCountries() {
        withContext(Dispatchers.IO) {
            val result = API.retrofitService.getCountries()
            database.countryDao.insertAll(result.toDatabaseCountries())
        }
    }
}