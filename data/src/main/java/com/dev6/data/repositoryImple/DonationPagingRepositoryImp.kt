package com.dev6.data.repositoryImple

import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.remote.DonationRemoteSource
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.repository.DonaitonPagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class DonationPagingRepositoryImp
@Inject constructor(private val remoteSource: DonationRemoteSource) :
    DonaitonPagingRepository {
    override suspend fun getDonationData(userId: String): Flow<PagingData<DonationPostFeed>> {
        return Pager(PagingConfig(pageSize = 3)) {
            DonationPagingSourceImp(remoteSource,userId)
        }.flow
    }
}