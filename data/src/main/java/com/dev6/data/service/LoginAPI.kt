package com.dev6.data.service

import com.dev6.data.entity.UserEntitiy
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.entitiyRepo.UserEntityRepo
import retrofit2.http.*

interface LoginAPI {

    @FormUrlEncoded
    @POST("/v1/user/login")
    // Repo
    suspend fun login(@Body userEntitiy: UserEntityRepo): String


}