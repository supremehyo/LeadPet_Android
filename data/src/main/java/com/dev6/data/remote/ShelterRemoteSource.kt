package com.dev6.data.remote

import android.util.Log
import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.model.shelter.ShelterPagingResponse
import com.dev6.data.service.FeedAPI
import javax.inject.Inject

interface ShelterRemoteSource {
    suspend fun getShelterList(
        cityName: String,
        page: Int,
        shelterName: String,
        size: Int
    ): ShelterPagingResponse?
}

class ShelterRemoteSourceImpl @Inject constructor(
    private val feedService: FeedAPI
) : ShelterRemoteSource {

    override suspend fun getShelterList(
        cityName: String,
        page: Int,
        shelterName: String,
        size: Int
    ): ShelterPagingResponse? {
        return feedService.nearShelterList(cityName, page, shelterName, size).executeNetworkHandling()
    }
}