package com.example.countriescodechallenge.rest

import com.example.countriescodechallenge.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {

    @GET(ENDPOINT)
    suspend fun getCountries() : Response<List<Country>>

    companion object {
        // https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json
        const val BASE_URL =
            "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
        private const val ENDPOINT = "countries.json"
    }
}