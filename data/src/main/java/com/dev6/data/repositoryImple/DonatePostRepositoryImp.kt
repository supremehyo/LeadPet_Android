package com.dev6.data.repositoryImple

import com.dev6.data.remote.DonatePostRemoteSource
import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import com.dev6.domain.repository.DonatePostRepository
import javax.inject.Inject

class DonatePostRepositoryImp @Inject constructor(private val remoteSource: DonatePostRemoteSource) :
    DonatePostRepository {

    override suspend fun insertDonatePost(postEntity: DonatePostEntityRepo): Boolean =
        remoteSource.insert(postEntity)
}