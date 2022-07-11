package com.dev6.domain.entitiyRepo

data class AdoptPostFeed(
    val adoptionPostId: String,
    val animalType: Any,
    val contents: String,
    val endDate: Any,
    val euthanasiaDate: Any,
    val gender: Any,
    val images: List<Any>,
    val neutering: Any,
    val species: Any,
    val startDate: Any,
    val title: String,
    val userId: String
)