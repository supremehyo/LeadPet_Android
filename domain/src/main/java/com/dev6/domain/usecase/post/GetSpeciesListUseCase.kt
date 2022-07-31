package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo
import com.dev6.domain.entitiyRepo.LifePost
import com.dev6.domain.entitiyRepo.Species
import com.dev6.domain.repository.AdoptPostRepository
import com.dev6.domain.repository.SpeciesRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias GetSpeciesListBaseUseCase = BaseUseCase<Unit, Flow<UiState<List<Species>>>>

//todo 일단 가볍게 테스트로만 사용
class GetSpeciesListUseCase(/*private val repo: SpeciesRepository*/) : GetSpeciesListBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<UiState<List<Species>>> = flow {
        emit(UiState.Loding)
        runCatching {
            listOf<Species>(
                Species("가", listOf("가브리안", "가비투엘", "가주망", "가지주")),
                Species("나", listOf("나루투", "나리토", "나미엘", "나무베")),
                Species("파", listOf("파파자", "파피주", "파피몬", "파라과이"))
            )
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

}