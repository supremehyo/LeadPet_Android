package com.dev6.domain.repository

import androidx.paging.PagingData
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface DailyPagingRepository {
    suspend fun getPagingData(userId: String, likedUser: String): Flow<PagingData<DailyPostFeed>>
    suspend fun postLike(postId: String, userId: String): ResponseBody
}
