package com.dev6.data.service

import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo
import retrofit2.http.Body
import retrofit2.http.POST

interface AdoptPostAPI {
    @POST("/v1/post/normal")
    suspend fun addNewAdoptPost(@Body adoptPostEntityRepo: AdoptPostEntityRepo): Boolean
}