package com.example.mtproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mtproject.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var m_binding: ActivityMainBinding
    private val m_mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(this.application)).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create binding
        m_binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(m_binding.root)

        m_binding.editCountryField = m_binding.editCountry
        m_binding.mainViewModel = m_mainViewModel
        m_binding.owner = this
        m_binding.lifecycleOwner = this

        m_mainViewModel.refreshDataFromRepository()
    }

    fun goToOverview() {
        Handler(Looper.getMainLooper()).postDelayed( {
            val intent = Intent(this@MainActivity, OverviewActivity::class.java)
            startActivity(intent)
        }, 500)
    }
}