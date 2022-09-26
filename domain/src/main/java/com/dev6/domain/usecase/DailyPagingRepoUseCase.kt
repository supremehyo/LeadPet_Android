package com.dev6.domain.usecase

import com.dev6.core.base.UiState
import com.dev6.domain.repository.daily.DailyRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DailyPagingRepoUseCase @Inject constructor(
    private val dailyPagingRepository: DailyRepository
) {

    suspend fun getDailyDataPagingData(userId: String, likedUser: String) =
        dailyPagingRepository.getPagingData(userId, likedUser)

    // Paging UseCase 에 postLike가 들어가는 단일책임원칙에 위배되는것 같아용
    // 해당 Like를 따로 분리하는게 좀더 좋을것같아요.
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
