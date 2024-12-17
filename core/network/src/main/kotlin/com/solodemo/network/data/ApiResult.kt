package com.solodemo.network.data

sealed class ApiResult<out T> {
    data class Success<out T>(val result: T) : ApiResult<T>()
    data class Error(val message: String, val code: Int? = null) : ApiResult<Nothing>()
}
