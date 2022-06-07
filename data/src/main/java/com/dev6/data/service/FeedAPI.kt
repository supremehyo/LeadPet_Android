package com.dev6.data.service

import com.dev6.data.entity.DailyFeedEntitiy
import com.dev6.data.entity.JoinEntitiy
import retrofit2.http.*

interface FeedAPI {

    @Headers("Content-Type: application/json")
    @GET("/v1/post/normal/all")
    suspend fun normalAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): List<DailyFeedEntitiy>

    @Headers("Content-Type: application/json")
    @GET("/v1/post/normal/allCount")
    suspend fun normalAllCount(): String
}