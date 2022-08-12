package com.dev6.domain.repository.adopt

import androidx.paging.PagingData
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import kotlinx.coroutines.flow.Flow

interface AdoptPagingRepository {
    suspend fun getAdoptData() : Flow<PagingData<AdoptPostFeed>>
}