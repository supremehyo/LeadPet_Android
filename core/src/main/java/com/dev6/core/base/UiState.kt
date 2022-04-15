package com.dev6.core.base

sealed class UiState<out T : Any> {

    object Loding : UiState<Nothing>()
    data class Success<T : Any>(val data : T) : UiState<T>()
    data class Error(val error: Throwable?) : UiState<Nothing>()

}