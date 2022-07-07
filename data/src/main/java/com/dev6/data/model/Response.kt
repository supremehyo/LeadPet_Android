package com.dev6.data.model

import com.dev6.data.util.DefaultHandleServerStatus
import com.google.gson.Gson
import com.jydev.rest_api_util.extension.executeErrorHandling
import retrofit2.Response

//data class Response<T>(
//    val error: ErrorResponse? = null,
//    val data: T
//) {
//    suspend fun executeNetworkHandling(): T {
//        val handle = error?.let {
//            DefaultHandleServerStatus(it)
//        } ?: null
//
//        return data.executeNetworkHandling(handle)
//
//    }
//}
//
data class ErrorResponse(
    val error: Error
)

data class Error(
    val code: Int, val message: String, val detail: String
)

suspend fun <T> Response<T>.executeNetworkHandling(): T? {

    val handle = if (isSuccessful) {
        null
    } else {
        val gson = Gson()
        val errorResponse = gson.fromJson(errorBody()?.string(), ErrorResponse::class.java)
        DefaultHandleServerStatus(errorResponse.error)
    }


    return body().executeErrorHandling(handle)
}

//fun <T> Response<Response1>.executeNetworkHandling(): T {
//    val handle = body()?.error?.let {
//        DefaultHandleServerStatus(it)
//    } ?: null
//
//    return executeNetworkHandling(handle)
//}

//abstract class Response1 {
//    val error: ErrorResponse? = null
//}