package com.dev6.data.remote

import com.dev6.data.service.LifePostAPI
import com.dev6.domain.entitiyRepo.LifePosEntityRepo
import javax.inject.Inject

interface LifePostRemoteSource {
    suspend fun insert(lifePosEntityRepo: LifePosEntityRepo): Boolean
}

class LifePostRemoteImpl @Inject constructor(
    private val lifePostAPI: LifePostAPI
) : LifePostRemoteSource {
    override suspend fun insert(lifePosEntityRepo: LifePosEntityRepo): Boolean =
        lifePostAPI.addNewLifePost(lifePosEntityRepo)
}