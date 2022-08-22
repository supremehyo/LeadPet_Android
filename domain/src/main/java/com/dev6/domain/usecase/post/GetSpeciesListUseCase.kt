package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.core.enum.AnimalType
import com.dev6.domain.entitiyRepo.Breed
import com.dev6.domain.entitiyRepo.IndexBreed
import com.dev6.domain.repository.BreedRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

//만약 값이 없다면 Unit
typealias GetSpeciesListBaseUseCase = BaseUseCase<Unit, Flow<UiState<List<IndexBreed>>>>

//todo 일단 가볍게 테스트로만 사용
class GetSpeciesListUseCase @Inject constructor(private val repo: BreedRepository) : GetSpeciesListBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<UiState<List<IndexBreed>>> = flow {
        emit(UiState.Loding)
        runCatching {
            repo.fetchBreedList()
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            Timber.e(it, "GetSpeciesListBaseUseCase ERROR")
            emit(UiState.Error(it))
        }
    }

}