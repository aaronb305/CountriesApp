package com.example.countriescodechallenge.rest

import com.example.countriescodechallenge.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception


class CountriesApiRepositoryImpl(
    private val countriesApi: CountriesApi
) : CountriesApiRepository {

    override fun getCountries(): Flow<ResponseState> = flow {
        try {
            val response = countriesApi.getCountries()
            if (response.isSuccessful) {
                response.body()?.let { list ->
                    emit(ResponseState.SUCCESS(list))
                } ?: throw Exception("Response is null")
            }
            else {
                throw Exception("Response is unsuccessful")
            }
        }
        catch (e : Exception) {
            emit(ResponseState.ERROR(e))
        }
    }
}

interface CountriesApiRepository {
    fun getCountries() : Flow<ResponseState>
}