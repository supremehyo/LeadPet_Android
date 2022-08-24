package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.daily.DailyPostFeed

interface LifePostRepository {
    suspend fun insertLifePost(postEntity: DailyPostFeed): DailyPostFeed
}