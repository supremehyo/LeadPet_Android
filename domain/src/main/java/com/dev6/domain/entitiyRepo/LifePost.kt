package com.dev6.domain.entitiyRepo

data class LifePost(
    val userId: String,
    val title: String,
    val contents: String,
    val images: List<String>,
    val normalPostId: String? = null
)