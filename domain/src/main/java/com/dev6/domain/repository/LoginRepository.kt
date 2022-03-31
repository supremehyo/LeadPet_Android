package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.LoginEntitiy
import com.dev6.domain.entitiyRepo.UserEntity
import retrofit2.Response

interface LoginRepository {
    suspend fun login(loginEntity : LoginEntitiy)  : Response<UserEntity>
}