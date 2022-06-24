package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import com.dev6.domain.repository.DonatePostRepository
import kotlinx.coroutines.flow.flow

class InsertDonatePostUseCase(private val repo: DonatePostRepository) {

    suspend operator fun invoke(entityRepo: DonatePostEntityRepo) = flow<UiState<Boolean>> {
        emit(UiState.Loding)
        runCatching {
            repo.insertDonatePost(entityRepo)
        }.onSuccess { result ->
            if (result) emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

}