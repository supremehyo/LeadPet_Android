package com.dev6.data.repositoryImp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.mapper.toDomain
import com.dev6.data.mapper.toMapper
import com.dev6.data.remote.DailyRemoteSource
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.daily.DailyPostRequest
import com.dev6.domain.repository.daily.DailyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject

class DailyRepositoryImp @Inject constructor(private val dailyRemoteSource: DailyRemoteSource) : DailyRepository {
    override suspend fun getPagingData(userId: String, likedUser: String): Flow<PagingData<DailyPost>> {
        return Pager(PagingConfig(pageSize = 3)) { DailyPagingSourceImp(userId, likedUser, dailyRemoteSource) }.flow
    }

    override suspend fun postLike(postId: String, userId: String): ResponseBody =
        dailyRemoteSource.postLike(postId, userId)

    override suspend fun insertDailyPost(dailyPost: DailyPostRequest): DailyPost =
        dailyRemoteSource.insert(dailyPost.toMapper()).toDomain()
}
