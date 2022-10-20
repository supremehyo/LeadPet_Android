package com.dev6.data.model

import com.dev6.core.enum.AnimalType

data class BreedListResponse(
    val results: List<ResultResponse>
)

data class ResultResponse(
    val index: String,
    val breedList: List<BreedResponse>
)

data class BreedResponse(
    val breedName: String,
    val animalType: AnimalType
)
