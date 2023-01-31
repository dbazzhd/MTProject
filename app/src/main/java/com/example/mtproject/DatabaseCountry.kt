package com.example.mtproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class DatabaseCountry constructor(
    val ActiveCases: String,
    @PrimaryKey
    val Name: String,
    val LastUpdate: String,
    val NewCases: String,
    val NewDeaths: String,
    val TotalCases: String,
    val TotalDeaths: String,
    val TotalRecovered: String
)

fun List<DatabaseCountry>.toCountries(): List<Country> {
    return map {
        Country(
            ActiveCases = it.ActiveCases,
            Name = it.Name,
            LastUpdate = it.LastUpdate,
            NewCases = it.NewCases,
            NewDeaths = it.NewDeaths,
            TotalCases = it.TotalCases,
            TotalDeaths = it.TotalDeaths,
            TotalRecovered = it.TotalRecovered
        )
    }
}