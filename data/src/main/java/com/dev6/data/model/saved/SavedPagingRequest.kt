package com.dev6.data.model.saved

data class SavedPagingRequest(
    val page: Int,
    val size: Int,
    val userId: String,
)
