package com.dev6.domain.repository.daily

import androidx.paging.PagingData
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.daily.DailyPostRequest
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface DailyRepository {
    suspend fun getPagingData(userId: String, likedUser: String): Flow<PagingData<DailyPost>>
    suspend fun postLike(postId: String, userId: String): ResponseBody
    suspend fun insertDailyPost(postEntity: DailyPostRequest): DailyPost
}
