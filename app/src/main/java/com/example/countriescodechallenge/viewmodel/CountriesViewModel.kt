package com.example.countriescodechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriescodechallenge.rest.CountriesApiRepository
import com.example.countriescodechallenge.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesApiRepository: CountriesApiRepository
) : ViewModel() {

    private val _countries : MutableLiveData<ResponseState> = MutableLiveData(ResponseState.LOADING)
    val countries : LiveData<ResponseState> get() = _countries

    fun getCountries() {
        _countries.postValue(ResponseState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            countriesApiRepository.getCountries().collect { state ->
                _countries.postValue(state)
            }
        }
    }
}