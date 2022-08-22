package com.dev6.domain.usecase

import com.dev6.core.base.UiState
import com.dev6.domain.repository.ShelterPagingRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ShelterPagingRepoUseCase @Inject constructor
    (
    private val shelterPagingRepository: ShelterPagingRepository
) {

    fun getShelterPagingData(cityName:String,shelterName:String) = flow {
        emit(UiState.Loding)
        runCatching {
            shelterPagingRepository.getPagingData(cityName, shelterName)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}

