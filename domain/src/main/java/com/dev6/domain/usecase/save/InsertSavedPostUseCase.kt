package com.dev6.domain.usecase.save

import com.dev6.core.base.UiState
import com.dev6.domain.model.save.Save
import com.dev6.domain.model.save.SavedPost
import com.dev6.domain.repository.saved.SavedRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias InsertSavedPostBaseUseCase = BaseUseCase<SavedPost, Flow<UiState<Save>>>

class InsertSavedPostUseCase(private val repo: SavedRepository) : InsertSavedPostBaseUseCase {

    override suspend fun invoke(savedPost: SavedPost): Flow<UiState<Save>> = flow {
        emit(UiState.Loding)
        runCatching {
            repo.insertSavedPost(savedPost)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
