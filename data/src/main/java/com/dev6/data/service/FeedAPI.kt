package com.dev6.data.service

import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.donation.DonationPaginationResponse
import retrofit2.http.*

interface FeedAPI {


    @GET("/v1/post/normal")
    suspend fun normalAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): DailyPaginationResponse


    @GET("/v1/post/donation")
    suspend fun donationAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): DonationPaginationResponse

    @GET("/v1/post/donation")
    suspend fun adoptAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): AdoptPaginationResponse
}