package com.dev6.data.model

import com.dev6.core.enum.AnimalType

data class BreedIndexResponse(
    val index: String,
    val breedList: List<BreedResponse>
)

data class BreedResponse(
    val animalType: AnimalType,
    val breedName: String
)

