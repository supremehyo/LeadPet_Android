package com.dev6.domain.usecase.login

import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias JoinReposBaseUseCase = BaseUseCase<JoinEntitiyRepo, Flow<UiState<String>>>

class JoinReposUseCase @Inject constructor(private val joinRepository: JoinRepository) :
    JoinReposBaseUseCase {


    override suspend fun invoke(joinEntitiyRepo: JoinEntitiyRepo): Flow<UiState<String>> =
        flow {
            emit(UiState.Loding)
            runCatching {
                joinRepository.signUp(joinEntitiyRepo)
            }.onSuccess { result ->
                emit(UiState.Success(result))
            }.onFailure {
                emit(UiState.Error(it))
            }
        }

}