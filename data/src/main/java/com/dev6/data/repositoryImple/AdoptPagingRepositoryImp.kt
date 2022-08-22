package com.dev6.data.repositoryImple

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.remote.AdoptRemoteSource
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.domain.repository.adopt.AdoptPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdoptPagingRepositoryImp
@Inject constructor(private val remoteSource: AdoptRemoteSource) :
    AdoptPagingRepository {
    override suspend fun getAdoptData(userId: String): Flow<PagingData<AdoptPostFeed>> {
        return Pager(PagingConfig(pageSize = 3)) {
            AdoptPagingSourceImp(remoteSource , userId)
        }.flow
    }
}