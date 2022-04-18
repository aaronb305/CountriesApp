package com.example.countriescodechallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriescodechallenge.databinding.CountriesItemBinding
import com.example.countriescodechallenge.model.Country

class CountriesAdapter(
    private val countriesList: MutableList<Country> = mutableListOf()
) : RecyclerView.Adapter<CountriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = CountriesItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countriesList[position])
    }

    override fun getItemCount(): Int = countriesList.size

    fun updateCountries(newCountries : List<Country>) {
        countriesList.clear()
        countriesList.addAll(newCountries)
        notifyDataSetChanged()
    }
}

class CountriesViewHolder(
    private val binding : CountriesItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(country: Country) {
        val name = country.name
        binding.name.text = "$name, "
        binding.capital.text = country.capital
        binding.region.text = country.region
        binding.countryCode.text = country.code
    }
}