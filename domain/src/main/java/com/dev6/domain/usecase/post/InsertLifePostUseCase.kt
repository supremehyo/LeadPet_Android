package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.LifePostEntityRepo
import com.dev6.domain.repository.LifePostRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertLifePostUseCase @Inject constructor (private val repo: LifePostRepository) {
    suspend operator fun invoke(entityRepo: LifePostEntityRepo) = flow<UiState<Boolean>> {
        emit(UiState.Loding)
        runCatching {
            repo.insertLifePost(entityRepo)
        }.onSuccess { result ->
            if (result) emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}