package com.dev6.data.service

import com.dev6.data.entity.adopt.AdoptPaginationResponse
import com.dev6.data.entity.daily.DailyPaginationResponse
import com.dev6.data.entity.donation.DonationPaginationResponse
import retrofit2.Response
import retrofit2.http.*

interface FeedAPI {

    @Headers("Content-Type: application/json")
    @GET("/v1/post/normal")
    suspend fun normalAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): DailyPaginationResponse

    @Headers("Content-Type: application/json")
    @GET("/v1/post/donation")
    suspend fun donationAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): DonationPaginationResponse

    @Headers("Content-Type: application/json")
    @GET("/v1/post/donation")
    suspend fun adoptAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): AdoptPaginationResponse
}