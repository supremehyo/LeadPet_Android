package com.dev6.data.service


import com.dev6.data.model.LifePostRequestResponse
import com.dev6.domain.entitiyRepo.LifePost
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST

interface LifePostAPI {
    @POST("/v1/post/normal")
    suspend fun addNewLifePost(@Body lifePostRequest: LifePostRequestResponse): Response<LifePostRequestResponse>
}