package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.JoinEntitiyRepo

interface AdoptRepository {
    suspend fun signUp(userEntitiy: JoinEntitiyRepo)  : String
}