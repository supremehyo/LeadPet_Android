package com.dev6.data.remote

import com.dev6.data.model.LifePostRequestResponse
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.service.LifePostAPI
import javax.inject.Inject

interface LifePostRemoteSource {
    suspend fun insert(lifePostEntityRepo: LifePostRequestResponse): LifePostRequestResponse?
}

class LifePostRemoteImpl @Inject constructor(
    private val lifePostAPI: LifePostAPI
) : LifePostRemoteSource {
    override suspend fun insert(lifePostEntityRepo: LifePostRequestResponse): LifePostRequestResponse? =
        lifePostAPI.addNewLifePost(lifePostEntityRepo).executeNetworkHandling()
}