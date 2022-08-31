package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.model.IndexBreed
import com.dev6.domain.repository.BreedRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias GetSpeciesListBaseUseCase = BaseUseCase<Unit, Flow<UiState<List<IndexBreed>>>>

class GetSpeciesListUseCase @Inject constructor(private val repo: BreedRepository) : GetSpeciesListBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<UiState<List<IndexBreed>>> = flow {
        emit(UiState.Loding)
        runCatching {
            repo.fetchBreedList()
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
