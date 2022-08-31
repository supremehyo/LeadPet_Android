package com.dev6.domain.repository

import com.dev6.domain.model.Join

interface JoinRepository {
    suspend fun signUp(userEntitiy: Join): String
}
