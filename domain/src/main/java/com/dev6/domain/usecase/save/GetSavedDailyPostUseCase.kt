package com.dev6.domain.usecase.save

import com.dev6.core.base.UiState
import com.dev6.domain.repository.saved.SavedRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSavedDailyPostUseCase @Inject constructor(private val repo: SavedRepository)
{

    fun getSavedDailyPagingData(userId: String) = flow{
        emit(UiState.Loding)
        runCatching {
            repo.getSavedDailyPagingData(userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
