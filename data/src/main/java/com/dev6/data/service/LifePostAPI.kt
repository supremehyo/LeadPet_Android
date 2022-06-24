package com.dev6.data.service

import com.dev6.domain.entitiyRepo.LifePostEntityRepo
import retrofit2.http.Body
import retrofit2.http.POST

interface LifePostAPI {
    @POST("/v1/post/adoption")
    suspend fun addNewLifePost(@Body lifePosEntityRepo: LifePostEntityRepo): Boolean
}