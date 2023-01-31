package com.example.mtproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtproject.databinding.ActivityOverviewBinding

class OverviewActivity : AppCompatActivity() {

    private lateinit var m_binding: ActivityOverviewBinding
    private val m_overviewViewModel: OverviewViewModel by lazy {
        ViewModelProvider(this, OverviewViewModel.Factory(this.application)).get(OverviewViewModel::class.java)
    }

    private lateinit var m_linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create binding
        m_binding = ActivityOverviewBinding.inflate(layoutInflater)
        setContentView(m_binding.root)

        m_overviewViewModel.refreshDataFromRepository() { adapter ->
            initializeRecyclerView(adapter)
        }

    }

    override fun onStart() {
        super.onStart()
        //initializeRecyclerView(m_overviewViewModel.adapter)
    }

    fun initializeRecyclerView(adapter: RecyclerViewAdapter) {
        m_linearLayoutManager = LinearLayoutManager(this)
        m_linearLayoutManager.orientation =RecyclerView.VERTICAL
        m_binding.recyclerviewCountries.layoutManager = m_linearLayoutManager
        m_binding.recyclerviewCountries.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun goToMain() {
        Handler(Looper.getMainLooper()).postDelayed( {
            val intent = Intent(this@OverviewActivity, MainActivity::class.java)
            startActivity(intent)
        }, 500)
    }
}