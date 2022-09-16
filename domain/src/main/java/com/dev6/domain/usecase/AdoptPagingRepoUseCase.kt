package com.dev6.domain.usecase

import com.dev6.core.base.UiState
import com.dev6.domain.repository.adopt.AdoptRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdoptPagingRepoUseCase @Inject constructor(
    private val adoptPagingRepository: AdoptRepository
) {

    fun getAdoptDataPagingData(userId: String) = flow {
        emit(UiState.Loding)
        runCatching {
            adoptPagingRepository.getAdoptData(userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
