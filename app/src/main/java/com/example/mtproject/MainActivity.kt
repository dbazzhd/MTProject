package com.example.mtproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mtproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val m_APIKey: String = "258bef614dmsh66cb9d23d2af467p104408jsnd02ea168d3ba"
    private val m_host: String = "covid-19-tracking.p.rapidapi.com"

    private lateinit var m_binding: ActivityMainBinding
    private lateinit var m_mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create binding
        m_binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(m_binding.root)
        // Create ViewModel
        m_mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        m_binding.editCountryField = m_binding.editCountry
        m_binding.mainViewModel = m_mainViewModel
        m_binding.lifecycleOwner = this

        REST.initialize(m_APIKey, m_host)
    }
}