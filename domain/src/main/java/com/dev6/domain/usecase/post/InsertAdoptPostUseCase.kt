package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.repository.adopt.AdoptRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias InsertAdoptPostBaseUseCase = BaseUseCase<AdoptPostFeed, Flow<UiState<AdoptPostFeed>>>

class InsertAdoptPostUseCase @Inject constructor(private val repo: AdoptRepository) :
    InsertAdoptPostBaseUseCase {
    override suspend fun invoke(params: AdoptPostFeed): Flow<UiState<AdoptPostFeed>> = flow {
        emit(UiState.Loding)
        runCatching {
            repo.insertAdoptPost(params)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}