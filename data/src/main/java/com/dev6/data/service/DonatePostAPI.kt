package com.dev6.data.service

import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import retrofit2.http.Body
import retrofit2.http.POST

interface DonatePostAPI {
    @POST("/v1/post/donation")
    suspend fun addNewDonatePost(@Body donatePostEntityRepo: DonatePostEntityRepo): Boolean
}