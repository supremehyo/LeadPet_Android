package com.dev6.data.remote

import com.dev6.data.model.BreedListResponse
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.service.BreedAPI
import javax.inject.Inject

interface BreedRemoteSource {
    suspend fun getBreedList(): BreedListResponse
}

class BreedRemoteSourceImpl @Inject constructor(
    private val breedService: BreedAPI
) : BreedRemoteSource {
    override suspend fun getBreedList() = breedService.fetchBreedList().executeNetworkHandling()
}
