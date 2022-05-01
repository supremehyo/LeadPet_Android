package com.dev6.data.repositoryImple

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.remote.DailyRemoteSource
import com.dev6.domain.repository.PagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingRepositoryImp @Inject constructor(private val dailyRemoteSource: DailyRemoteSource) : PagingRepository() {
    override fun getPagingData(): Flow<PagingData<Any>> {
        return Pager(PagingConfig(pageSize = 10)) { SamplePagingSourceImp(dailyRemoteSource)}.flow
    }
}