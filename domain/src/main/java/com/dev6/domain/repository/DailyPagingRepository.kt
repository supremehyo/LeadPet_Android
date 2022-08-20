package com.dev6.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject

interface DailyPagingRepository {
    suspend fun getPagingData(userId: String , likedUser : String) : Flow<PagingData<DailyPostFeed>>
    suspend fun postLike(postId: String, userId: String) : ResponseBody
}