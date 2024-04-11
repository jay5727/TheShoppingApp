package com.jay.theshoppingapp.core.result

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val ex: Exception) : Result<T>()
    object Loading : Result<Nothing>()
    object NoInternet : Result<Nothing>()
}