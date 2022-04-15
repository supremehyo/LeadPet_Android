package com.dev6.data.service

import com.dev6.domain.entitiyRepo.LoginEntitiy
import com.dev6.domain.entitiyRepo.UserEntity
import retrofit2.Response
import retrofit2.http.*

interface LoginAPI {


    @POST("/v1/user/login")
    // Repo
    suspend fun login(@Body loginEntitiy: LoginEntitiy): Response<UserEntity>


}