package com.dev6.data.remote

import com.dev6.data.service.LifePostAPI
import com.dev6.domain.entitiyRepo.LifePostEntityRepo
import javax.inject.Inject

interface LifePostRemoteSource {
    suspend fun insert(lifePosEntityRepo: LifePostEntityRepo): Boolean
}

class LifePostRemoteImpl @Inject constructor(
    private val lifePostAPI: LifePostAPI
) : LifePostRemoteSource {
    override suspend fun insert(lifePosEntityRepo: LifePostEntityRepo): Boolean =
        lifePostAPI.addNewLifePost(lifePosEntityRepo)
}