package com.dev6.data.repositoryImp

import androidx.annotation.WorkerThread
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.BreedRemoteSource
import com.dev6.domain.model.IndexBreed
import com.dev6.domain.repository.BreedRepository
import javax.inject.Inject

class BreedRepositoryImp @Inject constructor(private val remoteSource: BreedRemoteSource) : BreedRepository {
    @WorkerThread
    override suspend fun fetchBreedList(): List<IndexBreed> = remoteSource.getBreedList().map { it.toDomain() }
}