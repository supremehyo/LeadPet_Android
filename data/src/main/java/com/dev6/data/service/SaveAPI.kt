package com.dev6.data.service

import com.dev6.core.enums.PostType
import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.donation.DonationPaginationResponse
import com.dev6.data.model.saved.SavedResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface SaveAPI {
    @POST("/v1/savedPost")
    suspend fun insertSavedPost(
        @Field("postId") postId: String,
        @Field("postType") postType: PostType,
        @Field("userId") userId: String

    ): Response<SavedResponse>

    @DELETE("/v1/savedPost")
    suspend fun deleteSavedPost(
        @Field("savedPostId") savedPostId: String,
        @Field("userId") userId: String
    ): Response<SavedResponse>

    @GET("/v1/savedPost/adoptionPost/{userId}")
    suspend fun getSavedAdoptionPost(
        @Field("page") page: Int,
        @Field("size") size: Int,
        @Field("userId") userId: String
    ): Response<AdoptPaginationResponse>

    @GET("/v1/savedPost/donationPost/{userId}")
    suspend fun getSavedDonationPost(
        @Field("page") page: Int,
        @Field("size") size: Int,
        @Field("userId") userId: String
    ): Response<DonationPaginationResponse>

    @GET("/v1/savedPost/normalPost/{userId}")
    suspend fun getSavedNormalPost(
        @Field("page") page: Int,
        @Field("size") size: Int,
        @Field("userId") userId: String
    ): Response<DailyPaginationResponse>
}
