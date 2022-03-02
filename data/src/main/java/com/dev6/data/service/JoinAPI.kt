package com.dev6.data.service

import com.dev6.data.entity.JoinEntitiy
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface  JoinAPI {

    @FormUrlEncoded
    @POST("v1/user/signup")
    suspend fun signUp(@Body userEntitiy: JoinEntitiyRepo): String

}