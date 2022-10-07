package com.dev6.data.service

import com.dev6.data.model.BreedListResponse
import retrofit2.Response
import retrofit2.http.GET

interface BreedAPI {

    @GET("v1/breed")
    suspend fun fetchBreedList(): Response<BreedListResponse>
}
