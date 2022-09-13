package com.dev6.domain.usecase.save

import com.dev6.core.base.UiState
import com.dev6.domain.model.save.DeleteSavedPost
import com.dev6.domain.model.save.Save
import com.dev6.domain.repository.saved.SavedRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias DeleteSavedPostBaseUseCase = BaseUseCase<DeleteSavedPost, Flow<UiState<Save>>>

class DeleteSavedPostUseCase(private val repo: SavedRepository) : DeleteSavedPostBaseUseCase {

    override suspend fun invoke(deleteSavedPost: DeleteSavedPost): Flow<UiState<Save>> = flow {
        emit(UiState.Loding)
        runCatching {
            repo.deleteSavedPost(deleteSavedPost)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
