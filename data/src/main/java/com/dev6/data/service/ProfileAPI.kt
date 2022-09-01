package com.dev6.data.service


import com.dev6.data.model.profile.ShelterUserDetailResponse
import com.dev6.data.model.profile.ShelterUserUpdateResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileAPI {

    //보호소 컨트롤러
    @GET("/v1/shelter/{userId}")
    suspend fun getShelterUserDetail(
        @Path("userId") userId: String
    ): Response<ShelterUserDetailResponse>

    @FormUrlEncoded
    @PUT("/v1/shelter/{userId}")
    suspend fun updateShelterData(
        @Path("userId") userId: String,
        @Body body: ShelterUserUpdateResponse
    ): Response<ResponseBody>

}