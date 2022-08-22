package com.dev6.data.repositoryImple

import androidx.annotation.WorkerThread
import com.dev6.data.remote.AdoptPostRemoteSource
import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo
import com.dev6.domain.repository.AdoptPostRepository
import javax.inject.Inject

class AdoptPostRepositoryImp @Inject constructor(private val remoteSource: AdoptPostRemoteSource) : AdoptPostRepository {
    @WorkerThread
    override suspend fun insertAdoptPost(postEntity: AdoptPostEntityRepo): Boolean =
        remoteSource.insert(postEntity)
}