package com.example.countriescodechallenge.di

import com.example.countriescodechallenge.rest.CountriesApi
import com.example.countriescodechallenge.rest.CountriesApiRepository
import com.example.countriescodechallenge.rest.CountriesApiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesLoggingInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesCountriesApi(okHttpClient: OkHttpClient) : CountriesApi =
        Retrofit.Builder()
            .baseUrl(CountriesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CountriesApi::class.java)
    @Provides
    fun providesCountriesRepository(countriesApi: CountriesApi) : CountriesApiRepository =
        CountriesApiRepositoryImpl(countriesApi)
}