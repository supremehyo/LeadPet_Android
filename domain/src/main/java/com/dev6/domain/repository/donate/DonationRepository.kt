package com.dev6.domain.repository.donate

import androidx.paging.PagingData
import com.dev6.domain.model.donate.DonationPost
import kotlinx.coroutines.flow.Flow

interface DonationRepository {
    suspend fun getDonationData(userId: String): Flow<PagingData<DonationPost>>
    suspend fun insertDonatePost(donateFeed: DonationPost): DonationPost
}
