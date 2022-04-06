package com.dev6.core.base

data class ApiResponse<T> (
    val result: T,
    val error :Error
    )


data class Error (
    val code  : Int,
    val message : String,
    val detail : String,
)