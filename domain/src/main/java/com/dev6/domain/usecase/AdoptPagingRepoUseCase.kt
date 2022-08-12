package com.dev6.domain.usecase

import androidx.paging.PagingData
import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.domain.repository.DonaitonPagingRepository
import com.dev6.domain.repository.adopt.AdoptPagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AdoptPagingRepoUseCase @Inject constructor
    (
    private val adoptPagingRepository: AdoptPagingRepository
) {

    fun getAdoptDataPagingData() = flow {
        emit(UiState.Loding)
        runCatching {
            adoptPagingRepository.getAdoptData()
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}

