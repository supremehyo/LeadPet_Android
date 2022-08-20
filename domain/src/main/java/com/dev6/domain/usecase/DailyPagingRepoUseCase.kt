package com.dev6.domain.usecase

import android.util.Log
import com.dev6.core.base.UiState
import com.dev6.domain.repository.DailyPagingRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DailyPagingRepoUseCase @Inject constructor
    (
    private val dailyPagingRepository: DailyPagingRepository
) {

    fun getDailyDataPagingData(userId: String, likedUser: String) = flow {
        emit(UiState.Loding)
        runCatching {
            dailyPagingRepository.getPagingData(userId, likedUser)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

    fun postLike(postId: String, userId: String) = flow {
        emit(UiState.Loding)
        runCatching {
            dailyPagingRepository.postLike(postId, userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}

