package com.dev6.data.repositoryImple

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.remote.DonationRemoteSource
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.repository.donate.DonationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DonationRepositoryImp
@Inject constructor(private val remoteSource: DonationRemoteSource) :
    DonationRepository {
    override suspend fun getDonationData(donationMethod : String ,userId: String): Flow<PagingData<DonationPost>> {
        return Pager(PagingConfig(pageSize = 3)) {
            DonationPagingSourceImp(remoteSource, userId,donationMethod)
        }.flow
    }

    override suspend fun insertDonatePost(donateFeed: DonationPost): DonationPost {
        TODO("Not yet implemented")
    }
}
