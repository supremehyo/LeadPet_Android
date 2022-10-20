package com.dev6.data.repositoryImp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.mapper.toData
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.SavedRemoteSource
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.*
import com.dev6.domain.repository.saved.SavedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedRepositoryImp @Inject constructor(
    private val savedRemoteSource: SavedRemoteSource
) : SavedRepository {
    override suspend fun getSavedDonationPagingData(userId: String): Flow<PagingData<SavedDonationData>> {
        return Pager(PagingConfig(pageSize = 9)) { SavedDonationPagingSourceImp(userId,savedRemoteSource) }.flow
    }

    override suspend fun getSavedAdoptionPagingData(userId: String): Flow<PagingData<SavedAdoptionData>> {
        return Pager(PagingConfig(pageSize = 9)) { SavedAdoptionPagingSourceImp(userId,savedRemoteSource) }.flow
    }

    override suspend fun getSavedDailyPagingData(userId: String): Flow<PagingData<SavedDailyData>> {
        return Pager(PagingConfig(pageSize = 9)) { SavedDailyPagingSourceImp(userId,savedRemoteSource) }.flow
    }

    override suspend fun insertSavedPost(savedPost: SavedPost): Save = savedRemoteSource.insertSavedPost(
        savedPost.toData()
    ).toDomain()

    override suspend fun deleteSavedPost(deleteSavedPost: DeleteSavedPost): Save =
        savedRemoteSource.deleteSavedPost(deleteSavedPost.toData()).toDomain()
}
