package com.dev6.domain.repository

import androidx.paging.PagingData
import com.dev6.domain.entitiyRepo.DonationPostFeed
import kotlinx.coroutines.flow.Flow

interface DonaitonPagingRepository {
    suspend fun getDonationData() : Flow<PagingData<DonationPostFeed>>
}