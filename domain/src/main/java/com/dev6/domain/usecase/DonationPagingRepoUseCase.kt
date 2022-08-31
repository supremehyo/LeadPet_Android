package com.dev6.domain.usecase

import com.dev6.core.base.UiState
import com.dev6.domain.repository.donate.DonaitonRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DonationPagingRepoUseCase @Inject constructor(
    private val donaitonPagingRepository: DonaitonRepository
) {

    fun getDonationPagingData(userId: String) = flow {
        emit(UiState.Loding)
        runCatching {
            donaitonPagingRepository.getDonationData(userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
