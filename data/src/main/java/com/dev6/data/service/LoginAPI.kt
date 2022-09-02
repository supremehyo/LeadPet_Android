package com.dev6.data.service

import com.dev6.data.model.login.LoginRequest
import com.dev6.data.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("/v1/user/login")
    // Repo
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
