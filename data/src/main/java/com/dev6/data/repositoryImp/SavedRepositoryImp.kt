package com.dev6.data.repositoryImp

import androidx.paging.PagingData
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.SavedRemoteSource
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.DeleteSavedPost
import com.dev6.domain.model.save.Save
import com.dev6.domain.model.save.SavedPost
import com.dev6.domain.repository.saved.SavedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedRepositoryImp @Inject constructor(
    private val savedRemoteSource: SavedRemoteSource
) : SavedRepository {
    override suspend fun getSavedDonationPagingData(userId: String): Flow<PagingData<DonationPost>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedAdoptionPagingData(userId: String): Flow<PagingData<AdoptPostFeed>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedDailyPagingData(userId: String): Flow<PagingData<DailyPost>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertSavedPost(savedPost: SavedPost): Save = savedRemoteSource.insertSavedPost(
        savedPost.postId,
        savedPost.postType,
        savedPost.userId
    ).toDomain()

    override suspend fun deleteSavedPost(deleteSavedPost: DeleteSavedPost): Save =
        savedRemoteSource.deleteSavedPost(
            deleteSavedPost.savedPostId,
            deleteSavedPost.userId
        ).toDomain()
}
