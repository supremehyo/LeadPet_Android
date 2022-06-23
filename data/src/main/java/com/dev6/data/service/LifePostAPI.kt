package com.dev6.data.service

import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.entitiyRepo.LifePosEntityRepo
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LifePostAPI {
    @POST("/v1/post/adoption")
    suspend fun addNewLifePost(@Body lifePosEntityRepo: LifePosEntityRepo): Boolean
}