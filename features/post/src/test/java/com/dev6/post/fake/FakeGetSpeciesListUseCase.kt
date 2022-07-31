package com.dev6.post.fake

import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.Species
import com.dev6.domain.usecase.post.GetSpeciesListBaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetSpeciesListUseCase : GetSpeciesListBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<UiState<List<Species>>> = flow {
        val speciesList = listOf<Species>(
            Species("가", listOf("가브리안", "가비투엘", "가주망", "가지주")),
            Species("나", listOf("나루투", "나리토", "나미엘", "나무베")),
            Species("파", listOf("파파자", "파피주", "파피몬", "파라과이"))
        )
        emit(UiState.Success(speciesList))
    }
}