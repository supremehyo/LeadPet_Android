package com.dev6.domain.model

import androidx.room.Entity
import com.dev6.core.enum.AnimalType

@Entity
data class IndexBreed(
    val index: String,
    val breedList: List<Breed>
)

data class Breed(
    val breedName: String,
    val animalType: AnimalType
)

fun List<IndexBreed>.extractIndex(): List<String> =
    map {
        it.index
    }

