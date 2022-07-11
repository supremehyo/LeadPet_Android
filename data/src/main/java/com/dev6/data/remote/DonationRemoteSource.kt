package com.dev6.data.remote

import android.util.Log
import com.dev6.data.entity.donation.DonationPaginationResponse
import com.dev6.data.service.FeedAPI
import javax.inject.Inject

interface DonationRemoteSource {
    suspend fun donationAllFeed(page : Int , size : Int): DonationPaginationResponse
}

class DonationRemoteSourceImpl @Inject constructor(
    private val feedService: FeedAPI
) : DonationRemoteSource {

    override suspend fun donationAllFeed(page: Int, size: Int): DonationPaginationResponse {
        var temp = feedService.donationAllFeed(page, size)
        Log.v("asdfgasdg" , temp.donationFeedEntitiyList.get(0).title)
        return feedService.donationAllFeed(page, size)
    }
}