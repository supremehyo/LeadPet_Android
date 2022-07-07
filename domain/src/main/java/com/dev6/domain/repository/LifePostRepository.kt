package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.LifePost

interface LifePostRepository {
    suspend fun insertLifePost(postEntity: LifePost): LifePost
}