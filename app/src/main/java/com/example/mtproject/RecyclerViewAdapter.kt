package com.example.mtproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mtproject.databinding.ItemCountryBinding

class RecyclerViewAdapter(val m_countries: List<Country>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = m_countries.get(position)
        holder.setData(
            country.Name,
            country.ActiveCases,
            country.NewCases,
            country.NewDeaths,
            country.TotalCases,
            country.TotalDeaths,
            country.TotalRecovered)

    }

    override fun getItemCount(): Int {
        return m_countries.size
    }

    inner class ViewHolder(val m_binding: ItemCountryBinding)
        : RecyclerView.ViewHolder(m_binding.root) {

        fun setData(name: String, activeCases: String, newCases: String, newDeaths: String, totalCases: String, totalDeaths: String, totalRecovered: String) {
            m_binding.textCountryName.text = name
            m_binding.textActiveCases.text = activeCases
            m_binding.textNewCases.text = newCases
            m_binding.textNewDeaths.text = newDeaths
            m_binding.textTotalCases.text = totalCases
            m_binding.textTotalDeaths.text = totalDeaths
            m_binding.textTotalRecovered.text = totalRecovered
        }
    }
}


