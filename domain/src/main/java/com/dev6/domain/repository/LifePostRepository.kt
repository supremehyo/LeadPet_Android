package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.LifePostEntityRepo

interface LifePostRepository {
    suspend fun insertLifePost(postEntity: LifePostEntityRepo): Boolean
}