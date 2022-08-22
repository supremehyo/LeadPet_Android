package com.dev6.data.service

import com.dev6.data.model.BreedIndexResponse
import com.dev6.data.model.JoinEntitiy
import com.dev6.domain.entitiyRepo.IndexBreed
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface BreedAPI {

    @GET("v1/breed")
    suspend fun fetchBreedList(): Response<List<BreedIndexResponse>>
}