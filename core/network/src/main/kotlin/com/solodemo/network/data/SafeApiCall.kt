package com.solodemo.network.data

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> safeApiCall(apiCall: suspend () -> T): Flow<ApiResult<T>> = flow {
    try {
        val response = apiCall()
        emit(ApiResult.Success(response))
    } catch (e: RedirectResponseException) {
        emit(ApiResult.Error(e.response.status.description, e.response.status.value))
    } catch (e: ClientRequestException) {
        emit(ApiResult.Error(e.response.status.description, e.response.status.value))
    } catch (e: ServerResponseException) {
        emit(ApiResult.Error(e.response.status.description, e.response.status.value))
    } catch (e: Exception) {
        emit(ApiResult.Error(e.message ?: "Unknown error"))
    }
}.flowOn(Dispatchers.IO)
