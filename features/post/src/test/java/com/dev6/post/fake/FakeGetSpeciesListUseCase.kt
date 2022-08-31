package com.dev6.post.fake

import com.dev6.core.base.UiState
import com.dev6.domain.model.IndexBreed
import com.dev6.domain.usecase.post.GetSpeciesListBaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetSpeciesListUseCase : GetSpeciesListBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<UiState<List<IndexBreed>>> = flow {
        val speciesList = listOf<IndexBreed>(
//            IndexBreed("가", listOf("가브리안", "가비투엘", "가주망", "가지주")),
//            IndexBreed("나", listOf("나루투", "나리토", "나미엘", "나무베")),
//            IndexBreed("파", listOf("파파자", "파피주", "파피몬", "파라과이"))
        )
        emit(UiState.Success(speciesList))
    }
}