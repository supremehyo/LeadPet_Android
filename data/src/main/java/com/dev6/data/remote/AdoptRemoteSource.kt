package com.dev6.data.remote

import com.dev6.data.model.adopt.AdoptFeedResponse
import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.service.FeedAPI
import javax.inject.Inject

interface AdoptRemoteSource {
    suspend fun AdoptAllFeed(page: Int, size: Int, userId: String): AdoptPaginationResponse
    suspend fun insertAdoptPost(adoptFeedResponse: AdoptFeedResponse): AdoptFeedResponse
}

class AdoptRemoteSourceImpl @Inject constructor(
    private val feedService: FeedAPI
) : AdoptRemoteSource {

    override suspend fun AdoptAllFeed(page: Int, size: Int, userId: String): AdoptPaginationResponse {
        return feedService.adoptAllFeed(page, size, userId).executeNetworkHandling()
    }

    override suspend fun insertAdoptPost(adoptFeedResponse: AdoptFeedResponse): AdoptFeedResponse {
        return feedService.insertAdoptPost(adoptFeedResponse).executeNetworkHandling()
    }
}
