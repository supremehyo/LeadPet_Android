package com.dev6.post.fake

import com.dev6.core.base.UiState
import com.dev6.core.exception.NotCorrectException
import com.dev6.domain.usecase.post.InsertLifePostBaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeInsertLifePostUseCase : InsertLifePostBaseUseCase {

    override suspend fun invoke(params: LifePost): Flow<UiState<LifePost>> = flow {
        if (params.title.isEmpty()) emit(UiState.Success(params)) else emit(UiState.Error(NotCorrectException("테스트")))
    }
}