package com.example.countriescodechallenge.views

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriescodechallenge.adapter.CountriesAdapter
import com.example.countriescodechallenge.databinding.FragmentHomeBinding
import com.example.countriescodechallenge.model.Country
import com.example.countriescodechallenge.utils.ResponseState
import com.example.countriescodechallenge.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel : CountriesViewModel by viewModels()

    private val countriesAdapter by lazy {
        CountriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            adapter = countriesAdapter
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.countries.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.LOADING -> {
                    binding.recycler.visibility = View.GONE
                    binding.loadingBar.visibility = View.VISIBLE
                }
                is ResponseState.SUCCESS<*> -> {
                    binding.recycler.visibility = View.VISIBLE
                    binding.loadingBar.visibility = View.GONE
                    val response = state.response as List<Country>
                    countriesAdapter.updateCountries(response)
                }
                is ResponseState.ERROR -> {
                    binding.recycler.visibility = View.GONE
                    binding.loadingBar.visibility = View.GONE
                    AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage(state.error.localizedMessage)
                        .setNegativeButton("Dismiss") {
                                DialogInterface, i ->
                            DialogInterface.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        viewModel.getCountries()
    }

    override fun onStop() {
        super.onStop()

        viewModel.countries.removeObservers(viewLifecycleOwner)
    }
}