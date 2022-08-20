package com.dev6.data.repositoryImple

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.remote.DailyRemoteSource
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.domain.repository.DailyPagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import javax.inject.Inject

class DailyRepositoryImp
@Inject constructor(private val dailyRemoteSource: DailyRemoteSource) : DailyPagingRepository {
    override suspend fun getPagingData(userId: String , likedUser : String): Flow<PagingData<DailyPostFeed>> {
        return Pager(PagingConfig(pageSize = 3))
        { DailyPagingSourceImp(userId,likedUser, dailyRemoteSource)}.flow
    }

    override suspend fun postLike(postId: String, userId: String): ResponseBody = dailyRemoteSource.postLike(postId, userId)

}