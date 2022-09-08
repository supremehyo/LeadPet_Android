package com.dev6.data.service

import com.dev6.data.model.adopt.AdoptFeedResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface SaveAPI {
    @POST("/v1/savedPost")
    suspend fun insertSavedPost(@Body adoptFeedResponse: AdoptFeedResponse): Response<AdoptFeedResponse>

    @DELETE("/v1/savedPost")
    suspend fun deleteSavedPost(@Body adoptFeedResponse: AdoptFeedResponse): Response<AdoptFeedResponse>

    @GET("/v1/savedPost/adoptionPost/{userId}")
    suspend fun getSavedAdoptionPost(@Body adoptFeedResponse: AdoptFeedResponse): Response<AdoptFeedResponse>

    @GET("/v1/savedPost/donationPost/{userId}")
    suspend fun getSavedDonationPost(@Body adoptFeedResponse: AdoptFeedResponse): Response<AdoptFeedResponse>

    @GET("/v1/savedPost/normalPost/{userId}")
    suspend fun getSavedNormalPost(@Body adoptFeedResponse: AdoptFeedResponse): Response<AdoptFeedResponse>
}
