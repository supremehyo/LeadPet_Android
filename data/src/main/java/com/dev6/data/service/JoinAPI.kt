package com.dev6.data.service
import com.dev6.data.entity.JoinEntitiy
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import retrofit2.http.*

interface  JoinAPI {
    @Headers("Content-Type: application/json")
    @POST("v1/user/signup")
    suspend fun signUp(@Body userEntitiy: JoinEntitiy): String

}