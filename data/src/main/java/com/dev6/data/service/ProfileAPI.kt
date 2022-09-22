package com.dev6.data.service


import com.dev6.data.model.profile.ShelterUserDetailResponse
import com.dev6.data.model.profile.ShelterUserUpdateResponse
import com.dev6.data.model.profile.UserDetailResponse
import com.dev6.data.model.profile.UserUpdateRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileAPI {

    //보호소 컨트롤러
    @GET("/v1/shelter/{userId}")
    suspend fun getShelterUserDetail(
        @Path("userId") userId: String
    ): Response<ShelterUserDetailResponse>

    //유저 컨트롤러
    @GET("/v1/user/{userId}")
    suspend fun getNormalUserDetail(
        @Path("userId") userId: String
    ): Response<UserDetailResponse>


    @PUT("/v1/user/{userId}")
    suspend fun updateNormalUserData(
        @Path("userId") userId: String,
        @Body body: UserUpdateRequest
    ): Response<ResponseBody>


    @PUT("/v1/shelter/{userId}")
    suspend fun updateShelterData(
        @Path("userId") userId: String,
        @Body body: ShelterUserUpdateResponse
    ): Response<ResponseBody>



}