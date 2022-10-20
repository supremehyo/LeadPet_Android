package com.dev6.domain.usecase.save

import com.dev6.core.base.UiState
import com.dev6.domain.repository.saved.SavedRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSavedDonationPostUseCase @Inject constructor (private val repo: SavedRepository) {

    fun getSavedDonationPagingData(userId: String) = flow{
        emit(UiState.Loding)
        runCatching {
            repo.getSavedDonationPagingData(userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}