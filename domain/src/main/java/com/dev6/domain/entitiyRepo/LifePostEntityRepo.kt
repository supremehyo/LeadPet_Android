package com.dev6.domain.entitiyRepo

interface LifePosEntityRepo {
    val userId: String
    val title: String
    val contents: String
    val images: List<String>
}