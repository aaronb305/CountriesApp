package com.example.countriescodechallenge.utils

sealed class ResponseState {
    object LOADING : ResponseState()
    class SUCCESS<T>(val response : T) : ResponseState()
    class ERROR(val error : Throwable) : ResponseState()
}
