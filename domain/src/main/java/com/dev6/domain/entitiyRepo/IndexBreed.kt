package com.dev6.domain.entitiyRepo

import androidx.room.Entity

@Entity
data class IndexBreed(
    val index: String,
    val breedList: List<Breed>
)

fun List<IndexBreed>.extractIndex(): List<String> =
    map {
        it.index
    }

