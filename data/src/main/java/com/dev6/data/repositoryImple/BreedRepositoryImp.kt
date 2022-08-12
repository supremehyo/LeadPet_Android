package com.dev6.data.repositoryImple

import com.dev6.domain.entitiyRepo.IndexBreed
import com.dev6.domain.repository.BreedRepository

class BreedRepositoryImp : BreedRepository {
    override suspend fun fetchBreedList(): List<IndexBreed> {
        TODO("Not yet implemented")
    }
}