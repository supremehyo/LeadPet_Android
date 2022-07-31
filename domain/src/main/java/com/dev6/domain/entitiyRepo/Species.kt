package com.dev6.domain.entitiyRepo

data class Species(
    val index: String,
    val speciesList: List<String>
)

fun List<Species>.extractIndex(): List<String> =
    map {
        it.index
    }

