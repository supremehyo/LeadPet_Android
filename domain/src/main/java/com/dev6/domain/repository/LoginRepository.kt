package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.UserEntityRepo

interface LoginRepository {
    suspend fun login(userEntity : UserEntityRepo)  : String
}