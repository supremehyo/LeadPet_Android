package com.dev6.data.repositoryImple

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.remote.DailyRemoteSource
import com.dev6.data.remote.ShelterRemoteSource
import com.dev6.domain.entitiyRepo.ShelterEntitiyRepo
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.domain.repository.DailyPagingRepository
import com.dev6.domain.repository.ShelterPagingRepository
import com.dev6.domain.repository.ShelterPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShelterRepositoryImp
@Inject constructor(private val shelterRemoteSource: ShelterRemoteSource) :
    ShelterPagingRepository {
    override suspend fun getPagingData(cityName:String,shelterName:String): Flow<PagingData<ShelterEntitiyRepo>> {
        return Pager(PagingConfig(pageSize = 10))
        { ShelterPagingSourceImp(shelterRemoteSource , cityName, shelterName) }.flow
    }

}