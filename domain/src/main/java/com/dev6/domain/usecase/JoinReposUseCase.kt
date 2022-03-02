package com.dev6.domain.usecase

import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.repository.JoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JoinReposUseCase
@Inject constructor (private val joinRepository: JoinRepository) {

     fun signUp(joinEntitiyRepo: JoinEntitiyRepo) : Flow<String> = flow{
        emit(joinRepository.signUp(joinEntitiyRepo))
    }

}