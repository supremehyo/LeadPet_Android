package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.LifePosEntityRepo

interface LifePostRepository {
    suspend fun insertLifePost(postEntity: LifePosEntityRepo): Boolean
}