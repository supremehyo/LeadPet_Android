package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.core.enum.AnimalType
import com.dev6.domain.entitiyRepo.Breed
import com.dev6.domain.entitiyRepo.IndexBreed
import com.dev6.domain.repository.BreedRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//만약 값이 없다면 Unit
typealias GetSpeciesListBaseUseCase = BaseUseCase<Unit, Flow<UiState<List<IndexBreed>>>>

//todo 일단 가볍게 테스트로만 사용
class GetSpeciesListUseCase @Inject constructor(private val repo: BreedRepository) : GetSpeciesListBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<UiState<List<IndexBreed>>> = flow {
        emit(UiState.Loding)
        runCatching {
            listOf<IndexBreed>(
                IndexBreed(
                    "가",
                    listOf(
                        Breed("가브리안", AnimalType.CAT),
                        Breed("가비투엘", AnimalType.DOG),
                        Breed("가주망", AnimalType.CAT),
                        Breed("가지주", AnimalType.CAT)
                    )
                ),
                IndexBreed(
                    "나", listOf(
                        Breed("나루투", AnimalType.DOG),
                        Breed("나리토", AnimalType.DOG),
                        Breed("나미엘", AnimalType.CAT),
                        Breed("나무베", AnimalType.DOG)
                    )
                ),
                IndexBreed(
                    "파", listOf(
                        Breed("파파자", AnimalType.DOG),
                        Breed("파피주", AnimalType.CAT),
                        Breed("파피몬", AnimalType.DOG),
                        Breed("파라과이", AnimalType.CAT)
                    )
                )
            )
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

}