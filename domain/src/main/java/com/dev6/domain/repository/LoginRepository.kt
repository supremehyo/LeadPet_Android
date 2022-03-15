package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.LoginEntityRepo
import com.dev6.domain.entitiyRepo.UserEntityRepo
import retrofit2.Response

interface LoginRepository {
    suspend fun login(loginEntity : LoginEntityRepo)  : Response<UserEntityRepo>
}