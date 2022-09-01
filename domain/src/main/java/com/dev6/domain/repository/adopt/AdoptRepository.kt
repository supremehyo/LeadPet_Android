package com.dev6.domain.repository.adopt

import androidx.paging.PagingData
import com.dev6.domain.model.adopt.AdoptPostFeed
import kotlinx.coroutines.flow.Flow

interface AdoptRepository {
    suspend fun getAdoptData(userId: String): Flow<PagingData<AdoptPostFeed>>
    suspend fun insertAdoptPost(adoptPostFeed: AdoptPostFeed): AdoptPostFeed
}
