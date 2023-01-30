package com.example.mtproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mtproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val m_APIKey: String = "258bef614dmsh66cb9d23d2af467p104408jsnd02ea168d3ba"
    private val m_host: String = "covid-19-tracking.p.rapidapi.com"

    private lateinit var m_binding: ActivityMainBinding
    private lateinit var m_viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create binding
        m_binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(m_binding.root)
        // Create ViewModel
        m_viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        m_binding.editCountryField = m_binding.editCountry
        m_binding.mainViewModel = m_viewModel
        m_binding.lifecycleOwner = this

        REST.initialize(m_APIKey, m_host)

    }

    fun updateCountryUI(country: Country?) {
        m_binding.textCountry.text = country?.Name
        m_binding.textLastUpdate.text = country?.LastUpdate
        m_binding.textActiveCases.text = country?.ActiveCases
        m_binding.textNewCases.text = country?.NewCases
        m_binding.textNewDeaths.text = country?.NewDeaths
        m_binding.textTotalCases.text = country?.TotalCases
        m_binding.textTotalDeaths.text = country?.TotalDeaths
        m_binding.textTotalRecovered.text = country?.TotalRecovered
    }
}