package com.dev6.data.mapper

import com.dev6.core.enum.AnimalType
import com.dev6.data.model.BreedIndexResponse
import com.dev6.data.model.BreedResponse
import com.dev6.domain.entitiyRepo.Breed
import com.dev6.domain.entitiyRepo.IndexBreed

internal fun BreedIndexResponse?.toDomain() = IndexBreed(
    index = this?.index ?: "",
    breedList = this?.breedList?.map { it.toDomain() } ?: listOf()
)

internal fun BreedResponse?.toDomain() = Breed(
    animalType = this?.animalType ?: AnimalType.DOG,
    breedName = this?.breedName ?: ""

)
