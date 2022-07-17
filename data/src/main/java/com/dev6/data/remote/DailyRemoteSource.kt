package com.dev6.data.remote

import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.service.FeedAPI
import timber.log.Timber
import javax.inject.Inject

interface DailyRemoteSource {
    suspend fun dailyAllFeed(page : Int , size : Int): DailyPaginationResponse
}

class DailyRemoteSourceImpl @Inject constructor(
    private val feedService: FeedAPI
) : DailyRemoteSource {

    override suspend fun dailyAllFeed(page: Int, size: Int): DailyPaginationResponse {
        var temp = feedService.normalAllFeed(page, size)
        Timber.tag("asdfgasdg").v(temp.dailyFeedEntitiy.get(0).title)
        return feedService.normalAllFeed(page, size)
    }
}