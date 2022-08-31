package com.dev6.domain.repository

import com.dev6.domain.model.IndexBreed

interface BreedRepository {
    suspend fun fetchBreedList(): List<IndexBreed>
}