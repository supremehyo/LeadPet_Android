package com.dev6.data.service


import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.donation.DonationPaginationResponse
import com.dev6.data.model.profile.ShelterUserDetailEntitiy
import com.dev6.data.model.profile.ShelterUserUpdateEntitiy
import com.dev6.data.model.shelter.ShelterPagingResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileAPI {

    //보호소 컨트롤러
    @GET("/v1/shelter/{userId}")
    suspend fun getShelterUserDetail(
        @Path("userId") userId: String
    ): Response<ShelterUserDetailEntitiy>

    @FormUrlEncoded
    @PUT("/v1/shelter/{userId}")
    suspend fun updateShelterData(
        @Path("userId") userId: String,
        @Body body: ShelterUserUpdateEntitiy
    ): Response<ResponseBody>

}