package com.dev6.domain.repository

import com.dev6.domain.model.Login
import com.dev6.domain.model.User
import retrofit2.Response

interface LoginRepository {
    suspend fun login(login: Login): Response<User>
}