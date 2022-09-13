package com.dev6.data.repositoryImp

import androidx.paging.PagingData
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.SavedRemoteSource
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.Save
import com.dev6.domain.repository.saved.SavedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedRepositoryImp @Inject constructor(
    private val savedRemoteSource: SavedRemoteSource
) : SavedRepository {
    override suspend fun getSavedDonationPagingData(userId: String): Flow<PagingData<DonationPost>> {
    }

    override suspend fun getSavedAdoptionPagingData(userId: String): Flow<PagingData<AdoptPostFeed>> {
    }

    override suspend fun getSavedDailyPagingData(userId: String): Flow<PagingData<DailyPost>> {
    }

    override suspend fun insertSavedPost(savedPostId: String, userId: String): Save =
        savedRemoteSource.insertSavedPost(
            savedPostId,
            userId
        ).toDomain()

    override suspend fun deleteSavedPost(savedPostId: String, userId: String): Save =
        savedRemoteSource.deleteSavedPost(
            savedPostId,
            userId
        ).toDomain()
}
