package com.dev6.data.repositoryImp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.mapper.toDomain
import com.dev6.data.mapper.toMapper
import com.dev6.data.remote.AdoptRemoteSource
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.repository.adopt.AdoptRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdoptRepositoryImp
@Inject constructor(private val remoteSource: AdoptRemoteSource) :
    AdoptRepository {
    override suspend fun getAdoptData(userId: String): Flow<PagingData<AdoptPostFeed>> {
        return Pager(PagingConfig(pageSize = 3)) {
            AdoptPagingSourceImp(remoteSource, userId)
        }.flow
    }

    override suspend fun insertAdoptPost(adoptPostFeed: AdoptPostFeed): AdoptPostFeed
    =        remoteSource.insertAdoptPost(adoptPostFeed.toMapper()).toDomain()
}
