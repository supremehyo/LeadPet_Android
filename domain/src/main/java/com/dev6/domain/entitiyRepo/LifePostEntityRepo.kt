package com.dev6.domain.entitiyRepo

interface LifePostEntityRepo {
    val userId: String
    val title: String
    val contents: String
    val images: List<String>
}