package com.dev6.data.service

import com.dev6.data.model.JoinEntitiy
import com.dev6.domain.entitiyRepo.IndexBreed
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BreedAPI {

    @POST("v1/user/signup")
    suspend fun fetchBreedList(): List<IndexBreed>
}