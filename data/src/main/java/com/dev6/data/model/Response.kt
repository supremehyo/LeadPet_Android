package com.dev6.data.model

import com.dev6.data.util.DefaultHandleServerStatus
import com.google.gson.Gson
import com.jydev.rest_api_util.extension.executeErrorHandling
import retrofit2.Response
import timber.log.Timber

data class ErrorResponse(
    val error: Error
)

data class Error(
    val code: Int,
    val message: String,
    val detail: String
)

suspend fun <T> Response<T>.executeNetworkHandling(): T {
    val handle = if (isSuccessful) {
        null
    } else {
        val gson = Gson()
        val errorBody = errorBody()!!.string()
        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
        DefaultHandleServerStatus(errorResponse.error)
    }

    return body().executeErrorHandling(handle)
}
