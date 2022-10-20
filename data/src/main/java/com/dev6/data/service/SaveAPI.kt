package com.dev6.data.service

import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.donation.DonationPaginationResponse
import com.dev6.data.model.saved.*
import retrofit2.Response
import retrofit2.http.*

interface SaveAPI {

    @POST("/v1/savedPost")
    suspend fun insertSavedPost(
        @Body savedPost: SavedRequest
    ): Response<SavedResponse>

    @HTTP(method = "DELETE", path = "/v1/savedPost", hasBody = true)
    suspend fun deleteSavedPost(
        @Body savedPost: DeleteSavedRequest
    ): Response<SavedResponse>

    @GET("/v1/savedPost/adoptionPost/{userId}")
    suspend fun getSavedAdoptionPost(
        @Path("userId") userId: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<SavedAdoptonPagingResponse>

    @GET("/v1/savedPost/donationPost/{userId}")
    suspend fun getSavedDonationPost(
        @Path("userId") userId: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<SavedDonationPagingResponse>

    @GET("/v1/savedPost/normalPost/{userId}")
    suspend fun getSavedNormalPost(
        @Path("userId") userId: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<SavedDailyPagingResponse>
}
