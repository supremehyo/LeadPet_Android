package com.dev6.data.service

import com.dev6.data.model.adopt.AdoptFeedResponse
import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.data.model.comment.CommentPaginationResponse
import com.dev6.data.model.comment.LikeRequestResponse
import com.dev6.data.model.daily.DailyFeedRequestResponse
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.donation.DonationFeedResponse
import com.dev6.data.model.donation.DonationPaginationResponse
import com.dev6.data.model.shelter.ShelterPagingResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface FeedAPI {
    @GET("/v1/post/normal")
    suspend fun normalAllFeed(
        @Query("likedUserId") likedUserId: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("userId") userId: String
    ): Response<DailyPaginationResponse>

    @GET("/v1/post/donation")
    suspend fun donationAllFeed(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("userId") userId: String
    ): Response<DonationPaginationResponse>

    @GET("/v1/post/adoption")
    suspend fun adoptAllFeed(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("userId") userId: String
    ): Response<AdoptPaginationResponse>

    @POST("/v1/post/adoption")
    suspend fun insertAdoptPost(@Body adoptFeedResponse: AdoptFeedResponse): Response<AdoptFeedResponse>

    @POST("/v1/post/donation")
    suspend fun insertDonatePost(@Body donateFeedResponse: DonationFeedResponse): Response<DonationFeedResponse>

    @POST("/v1/post/normal")
    suspend fun insertDailyPost(@Body dailyFeedRequestResponse: DailyFeedRequestResponse): Response<DailyFeedRequestResponse>

    @GET("/v1/shelter/list")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    suspend fun nearShelterList(
        @Query("cityName") cityName: String,
        @Query("page") page: Int,
        @Query("shelterName") shelterName: String?,
        @Query("size") size: Int
    ): Response<ShelterPagingResponse>

    @POST("/v1/liked")
    suspend fun postLike(
        @Body likeDTO: LikeRequestResponse
    ): Response<ResponseBody>

    @GET("/v1/reply/normal")
    suspend fun getDailyCommentList(
        @Query("page") page: Int,
        @Query("postId") postId: String,
        @Query("size") size: Int
    ): Response<CommentPaginationResponse>
}
