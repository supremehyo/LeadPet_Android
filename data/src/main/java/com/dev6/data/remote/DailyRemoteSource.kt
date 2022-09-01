package com.dev6.data.remote

import android.util.Log
import com.dev6.data.model.comment.LikeRequestResponse
import com.dev6.data.model.daily.DailyFeedRequestResponse
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.service.FeedAPI
import okhttp3.ResponseBody
import javax.inject.Inject

interface DailyRemoteSource {
    suspend fun dailyAllFeed(page: Int, size: Int, userId: String, likedUserId: String): DailyPaginationResponse
    suspend fun postLike(postId: String, userId: String): ResponseBody
    suspend fun insert(dailyFeedRequestResponse: DailyFeedRequestResponse): DailyFeedRequestResponse
}

class DailyRemoteSourceImpl @Inject constructor(
    private val feedService: FeedAPI
) : DailyRemoteSource {

    override suspend fun dailyAllFeed(
        page: Int,
        size: Int,
        userId: String,
        likedUserId: String
    ): DailyPaginationResponse {
        return feedService.normalAllFeed(likedUserId, page, size, userId).executeNetworkHandling()
    }

    override suspend fun postLike(postId: String, userId: String): ResponseBody {
        Log.v("lifkfkf4", postId + " " + userId)
        return feedService.postLike(LikeRequestResponse(postId, userId)).executeNetworkHandling()
    }

    override suspend fun insert(dailyFeedRequestResponse: DailyFeedRequestResponse): DailyFeedRequestResponse {
        return feedService.insertDailyPost(dailyFeedRequestResponse).executeNetworkHandling()
    }
}
