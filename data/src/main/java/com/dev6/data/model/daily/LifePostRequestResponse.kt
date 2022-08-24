package com.dev6.data.model.daily

data class LifePostRequestResponse(
    val userId: String,
    val title: String,
    val contents: String,
    val images: List<String>,
    val normalPostId: String?
)


