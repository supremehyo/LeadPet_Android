package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.IndexBreed

interface SpeciesRepository {
    suspend fun fetchSpeciesList(): List<IndexBreed>
}