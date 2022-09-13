package com.dev6.data.remote

import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.donation.DonationPaginationResponse
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.model.saved.SavedResponse
import com.dev6.data.service.SaveAPI
import javax.inject.Inject

interface SavedRemoteSource {
    suspend fun getSavedAdoptionPost(
        page: Int,
        size: Int,
        userId: String
    ): AdoptPaginationResponse

    suspend fun getSavedDonationPost(
        page: Int,
        size: Int,
        userId: String
    ): DonationPaginationResponse

    suspend fun getSavedDailyPost(
        page: Int,
        size: Int,
        userId: String

    ): DailyPaginationResponse

    suspend fun insertSavedPost(
        savedPostId: String,
        userId: String
    ): SavedResponse

    suspend fun deleteSavedPost(
        savedPostId: String,
        userId: String
    ): SavedResponse
}

class SavedRemoteSourceImp @Inject constructor(
    private val saveAPI: SaveAPI
) : SavedRemoteSource {

    override suspend fun getSavedAdoptionPost(page: Int, size: Int, userId: String): AdoptPaginationResponse =
        saveAPI.getSavedAdoptionPost(
            page = page,
            size = size,
            userId = userId
        ).executeNetworkHandling()

    override suspend fun getSavedDonationPost(page: Int, size: Int, userId: String): DonationPaginationResponse =
        saveAPI.getSavedDonationPost(
            page = page,
            size = size,
            userId = userId
        ).executeNetworkHandling()

    override suspend fun getSavedDailyPost(page: Int, size: Int, userId: String): DailyPaginationResponse =
        saveAPI.getSavedNormalPost(page = page, size = size, userId = userId).executeNetworkHandling()

    override suspend fun insertSavedPost(savedPostId: String, userId: String): SavedResponse =
        saveAPI.insertSavedPost(savedPostId, userId).executeNetworkHandling()

    override suspend fun deleteSavedPost(savedPostId: String, userId: String): SavedResponse =
        saveAPI.deleteSavedPost(savedPostId = savedPostId, userId = userId).executeNetworkHandling()
}
