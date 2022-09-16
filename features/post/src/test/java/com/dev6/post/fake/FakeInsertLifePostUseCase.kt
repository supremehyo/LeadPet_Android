package com.dev6.post.fake

//class FakeInsertLifePostUseCase : InsertLifePostBaseUseCase {
//
//    override suspend fun invoke(params: LifePost): Flow<UiState<LifePost>> = flow {
//        if (params.title.isEmpty()) emit(UiState.Success(params)) else emit(UiState.Error(NotCorrectException("테스트")))
//    }
//}