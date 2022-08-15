package com.dev6.data.repositoryImple

import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.AdoptPostRemoteSource
import com.dev6.data.remote.BreedRemoteSource
import com.dev6.domain.entitiyRepo.IndexBreed
import com.dev6.domain.repository.BreedRepository
import javax.inject.Inject

class BreedRepositoryImp @Inject constructor(private val remoteSource: BreedRemoteSource) : BreedRepository {
    override suspend fun fetchBreedList(): List<IndexBreed> = remoteSource.getBreedList().map { it.toDomain() }
}