package com.dev6.data.repositoryImple

import com.dev6.data.remote.LifePostRemoteSource
import com.dev6.domain.entitiyRepo.LifePostEntityRepo
import com.dev6.domain.repository.LifePostRepository
import javax.inject.Inject

class LifePostRepositoryImp @Inject constructor(private val remoteSource: LifePostRemoteSource) :
    LifePostRepository {
    override suspend fun insertLifePost(postEntity: LifePostEntityRepo): Boolean =
        remoteSource.insert(postEntity)
}