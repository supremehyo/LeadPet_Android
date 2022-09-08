package com.dev6.domain.repository.saved

import androidx.paging.PagingData
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.donate.DonationPost
import kotlinx.coroutines.flow.Flow

interface SavedRepository {
    suspend fun getSavedDonationPagingData(userId: String): Flow<PagingData<DonationPost>>
    suspend fun getSavedAdoptionPagingData(userId: String): Flow<PagingData<AdoptPostFeed>>
    suspend fun getSavedDailyPagingData(userId: String): Flow<PagingData<DailyPost>>
}
