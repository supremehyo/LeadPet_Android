package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo
import com.dev6.domain.entitiyRepo.Species

interface SpeciesRepository {
    suspend fun fetchSpeciesList(): List<Species>
}