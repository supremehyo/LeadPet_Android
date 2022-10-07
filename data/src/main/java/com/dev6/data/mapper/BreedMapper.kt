package com.dev6.data.mapper

import com.dev6.data.model.BreedResponse
import com.dev6.data.model.ResultResponse
import com.dev6.domain.model.Breed
import com.dev6.domain.model.IndexBreed

internal fun ResultResponse.toDomain() = IndexBreed(
    index = this.index,
    breedList = this.breedList.map { it.toDomain() }
)

internal fun BreedResponse.toDomain() = Breed(
    animalType = this.animalType,
    breedName = this.breedName
)
