package com.dev6.data.service

import com.dev6.data.entity.DailyFeedEntitiy
import com.dev6.data.entity.JoinEntitiy
import com.dev6.data.entity.donation.DonationPaginationResponse
import retrofit2.http.*

interface FeedAPI {

    @Headers("Content-Type: application/json")
    @GET("/v1/post/normal/all")
    suspend fun normalAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): List<DailyFeedEntitiy>

    @Headers("Content-Type: application/json")
    @GET("/v1/post/donation")
    suspend fun donationAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): DonationPaginationResponse



    @Headers("Content-Type: application/json")
    @GET("/v1/post/normal/allCount")
    suspend fun normalAllCount(): String
}