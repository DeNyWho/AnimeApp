package com.example.animeapp.util

sealed class Result<T>(val data:T? = null, val errorMessage: String? = null) {

    class Success<T>(data: T, errorMessage: String? = null): Result<T>(data = data, errorMessage = errorMessage)
    class Error<T>(errorMessage: String?, data: T? = null): Result<T>(data = data, errorMessage = errorMessage)
    class Loading<T>: Result<T>()

}
