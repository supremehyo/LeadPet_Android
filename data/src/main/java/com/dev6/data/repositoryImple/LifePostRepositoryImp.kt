package com.dev6.data.repositoryImple

import androidx.annotation.WorkerThread
import com.dev6.data.mapper.toDomain
import com.dev6.data.mapper.toMapper
import com.dev6.data.remote.LifePostRemoteSource
import com.dev6.domain.entitiyRepo.LifePost
import com.dev6.domain.repository.LifePostRepository
import javax.inject.Inject

class LifePostRepositoryImp @Inject constructor(private val remoteSource: LifePostRemoteSource) :
    LifePostRepository {
    @WorkerThread
    override suspend fun insertLifePost(postEntity: LifePost): LifePost =
        remoteSource.insert(postEntity.toMapper()).toDomain()
}