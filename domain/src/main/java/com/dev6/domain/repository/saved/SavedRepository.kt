package com.dev6.domain.repository.saved

import androidx.paging.PagingData
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.*
import kotlinx.coroutines.flow.Flow

interface SavedRepository {
    suspend fun getSavedDonationPagingData(userId: String): Flow<PagingData<SavedDonationData>>
    suspend fun getSavedAdoptionPagingData(userId: String): Flow<PagingData<SavedAdoptionData>>
    suspend fun getSavedDailyPagingData(userId: String): Flow<PagingData<SavedDailyData>>

    suspend fun insertSavedPost(savedPost: SavedPost): Save
    suspend fun deleteSavedPost(deleteSavedPost: DeleteSavedPost): Save
}
