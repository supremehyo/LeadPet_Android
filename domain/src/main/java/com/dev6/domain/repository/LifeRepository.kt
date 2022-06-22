package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import com.dev6.domain.entitiyRepo.LifePosEntityRepo

interface LifeRepository {
    suspend fun insertLifePost(postEntity: LifePosEntityRepo): Boolean
}