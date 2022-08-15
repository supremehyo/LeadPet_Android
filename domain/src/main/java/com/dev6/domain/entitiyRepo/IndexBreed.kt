package com.dev6.domain.entitiyRepo

import androidx.room.Entity
import com.dev6.core.enum.AnimalType

@Entity
data class IndexBreed(
    val index: String,
    val breedList: List<Breed>
)

data class Breed(
    val animalType: AnimalType,
    val breedName: String
)

fun List<IndexBreed>.extractIndex(): List<String> =
    map {
        it.index
    }

