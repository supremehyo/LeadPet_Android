package com.dev6.data.repositoryImple

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.remote.DailyRemoteSource
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.domain.repository.DailyPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DailyRepositoryImp
@Inject constructor(private val dailyRemoteSource: DailyRemoteSource) : DailyPagingRepository {
    override suspend fun getPagingData(): Flow<PagingData<DailyPostFeed>> {
        return Pager(PagingConfig(pageSize = 10))
        { DailyPagingSourceImp(dailyRemoteSource)}.flow
    }

}