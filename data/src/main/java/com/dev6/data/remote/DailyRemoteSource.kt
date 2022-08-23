package com.dev6.data.remote

import android.util.Log
import com.dev6.data.model.comment.LikeDTO
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.service.FeedAPI
import okhttp3.ResponseBody
import timber.log.Timber
import javax.inject.Inject

interface DailyRemoteSource {
    suspend fun dailyAllFeed(page: Int, size: Int ,userId:String ,likedUserId : String ): DailyPaginationResponse
    suspend fun postLike(postId: String, userId: String): ResponseBody
}

class DailyRemoteSourceImpl @Inject constructor(
    private val feedService: FeedAPI
) : DailyRemoteSource {

    override suspend fun dailyAllFeed(page: Int, size: Int ,userId:String ,likedUserId : String ): DailyPaginationResponse {
        return feedService.normalAllFeed(likedUserId,page, size,userId).executeNetworkHandling()
    }

    override suspend fun postLike(postId: String, userId: String): ResponseBody{
        Log.v("lifkfkf4",postId+" "+userId)
        return feedService.postLike(LikeDTO(postId, userId)).executeNetworkHandling()
    }

}