package com.dev6.domain.usecase

import androidx.paging.PagingData
import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.repository.DonaitonPagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DonationPagingRepoUseCase @Inject constructor
    (
    private val donaitonPagingRepository: DonaitonPagingRepository
) {

    fun getDonationPagingData() = flow {
        emit(UiState.Loding)
        runCatching {
            donaitonPagingRepository.getDonationData()
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}

