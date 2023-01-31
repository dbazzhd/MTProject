package com.example.mtproject

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("Active Cases_text")
    var ActiveCases: String = "N/A",
    @SerializedName("Country_text")
    var Name: String = "N/A",
    @SerializedName("Last Update")
    var LastUpdate: String = "N/A",
    @SerializedName("New Cases_text")
    var NewCases: String = "N/A",
    @SerializedName("New Deaths_text")
    var NewDeaths: String = "N/A",
    @SerializedName("Total Cases_text")
    var TotalCases: String = "N/A",
    @SerializedName("Total Deaths_text")
    var TotalDeaths: String = "N/A",
    @SerializedName("Total Recovered_text")
    var TotalRecovered: String = "N/A"
) {
    override fun toString(): String {
        return Name
    }
}
