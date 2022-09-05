package com.dev6.data.remote

import com.dev6.data.model.donation.DonationFeedResponse
import com.dev6.data.model.donation.DonationPaginationResponse
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.service.FeedAPI
import javax.inject.Inject

interface DonationRemoteSource {
    suspend fun donationAllFeed(donationMethod : String,page: Int, size: Int, userId: String): DonationPaginationResponse

    suspend fun insertDonationPost(donationFeedResponse: DonationFeedResponse): DonationFeedResponse
}

class DonationRemoteSourceImpl @Inject constructor(
    private val feedService: FeedAPI
) : DonationRemoteSource {

    override suspend fun donationAllFeed(donationMethod : String,page: Int, size: Int, userId: String): DonationPaginationResponse {
        return feedService.donationAllFeed(donationMethod,page, size, userId).executeNetworkHandling()
    }

    override suspend fun insertDonationPost(donationFeedResponse: DonationFeedResponse): DonationFeedResponse {
        return feedService.insertDonatePost(donationFeedResponse).executeNetworkHandling()
    }
}
