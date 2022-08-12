package com.dev6.data.service

import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.donation.DonationPaginationResponse
import com.dev6.data.model.shelter.ShelterPagingResponse
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

    @GET("/v1/post/adoption")
    suspend fun adoptAllFeed(
        @Query("page") page : Int,
        @Query("size") size : Int
    ): AdoptPaginationResponse

    @GET("/v1/shelter/list")
    suspend fun nearShelterList(
        @Query("cityName") cityName: String,
        @Query("page") page: Int,
        @Query("shelterName") shelterName: String?,
        @Query("size") size: Int
    ): ShelterPagingResponse

}