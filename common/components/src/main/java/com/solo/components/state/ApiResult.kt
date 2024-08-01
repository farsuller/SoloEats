package com.solo.components.state

sealed class ApiResult<out R> {

    data object Idle : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
    data class Success<out R>(val data: R) : ApiResult<R>()
    data class Error(val message: String) : ApiResult<Nothing>()
}
