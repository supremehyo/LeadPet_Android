package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.LoginEntity
import com.dev6.domain.entitiyRepo.UserEntity
import retrofit2.Response

interface LoginRepository {
    suspend fun login(loginEntity : LoginEntity)  : Response<UserEntity>
}