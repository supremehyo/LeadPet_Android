package com.dev6.domain.repository

import com.dev6.domain.model.Login
import com.dev6.domain.model.User

interface LoginRepository {
    suspend fun login(login: Login): User
}