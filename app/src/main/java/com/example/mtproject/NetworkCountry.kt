package com.example.mtproject

import com.squareup.moshi.Json

data class NetworkCountry(
    @Json(name = "Active Cases_text")
    var ActiveCases: String?,
    @Json(name = "Country_text")
    var Name: String?,
    @Json(name = "Last Update")
    var LastUpdate: String?,
    @Json(name = "New Cases_text")
    var NewCases: String?,
    @Json(name = "New Deaths_text")
    var NewDeaths: String?,
    @Json(name = "Total Cases_text")
    var TotalCases: String?,
    @Json(name = "Total Deaths_text")
    var TotalDeaths: String?,
    @Json(name = "Total Recovered_text")
    var TotalRecovered: String?
)

fun List<NetworkCountry>.toDatabaseCountries(): List<DatabaseCountry> {
    return map {
        DatabaseCountry(
            ActiveCases = (if (it.ActiveCases == null) "N/A" else it.ActiveCases!!),
            Name = (if (it.Name == null) "N/A" else it.Name!!),
            LastUpdate = (if (it.LastUpdate == null) "N/A" else it.LastUpdate!!),
            NewCases = (if (it.NewCases == null) "N/A" else it.NewCases!!),
            NewDeaths = (if (it.NewDeaths == null) "N/A" else it.NewDeaths!!),
            TotalCases = (if (it.TotalCases == null) "N/A" else it.TotalCases!!),
            TotalDeaths = (if (it.TotalDeaths == null) "N/A" else it.TotalDeaths!!),
            TotalRecovered = (if (it.TotalRecovered == null) "N/A" else it.TotalRecovered!!)
        )
    }
}

fun List<NetworkCountry>.toCountries(): List<Country> {
    return map {
        Country(
            ActiveCases = (if (it.ActiveCases == null) "N/A" else it.ActiveCases!!),
            Name = (if (it.Name == null) "N/A" else it.Name!!),
            LastUpdate = (if (it.LastUpdate == null) "N/A" else it.LastUpdate!!),
            NewCases = (if (it.NewCases == null) "N/A" else it.NewCases!!),
            NewDeaths = (if (it.NewDeaths == null) "N/A" else it.NewDeaths!!),
            TotalCases = (if (it.TotalCases == null) "N/A" else it.TotalCases!!),
            TotalDeaths = (if (it.TotalDeaths == null) "N/A" else it.TotalDeaths!!),
            TotalRecovered = (if (it.TotalRecovered == null) "N/A" else it.TotalRecovered!!)
        )
    }
}