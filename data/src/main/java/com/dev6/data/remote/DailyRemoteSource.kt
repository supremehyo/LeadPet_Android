package com.dev6.data.remote

import com.dev6.data.entity.DailyFeedEntitiy
import com.dev6.data.service.FeedAPI
import javax.inject.Inject

interface DailyRemoteSource {
    suspend fun normalAllFeed(page : Int , size : Int): List<DailyFeedEntitiy>
    suspend fun normalAllCount(): String
}

class DailyRemoteSourceImpl @Inject constructor(
    private val feedService: FeedAPI
) : DailyRemoteSource {
    override suspend fun normalAllFeed(page : Int , size : Int): List<DailyFeedEntitiy> {
        return feedService.normalAllFeed(page, size)
    }
    override suspend fun normalAllCount(): String {
        return feedService.normalAllCount()
    }
}