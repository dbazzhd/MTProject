package com.example.mtproject

import com.google.gson.annotations.SerializedName

data class Country(
    var ActiveCases: String = "N/A",
    var Name: String = "N/A",
    var LastUpdate: String = "N/A",
    var NewCases: String = "N/A",
    var NewDeaths: String = "N/A",
    var TotalCases: String = "N/A",
    var TotalDeaths: String = "N/A",
    var TotalRecovered: String = "N/A"
)