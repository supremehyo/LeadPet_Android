package com.dev6.data.service

import com.dev6.data.entity.UserEntity
import com.dev6.domain.entitiyRepo.LoginEntityRepo
import com.dev6.domain.entitiyRepo.UserEntityRepo
import retrofit2.Response
import retrofit2.http.*

interface LoginAPI {

    @FormUrlEncoded
    @POST("/v1/user/login")
    // Repo
    suspend fun login(@Body loginEntitiy: LoginEntityRepo): Response<UserEntityRepo>


}