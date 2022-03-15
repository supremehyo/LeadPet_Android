package com.dev6.domain.usecase

import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.entitiyRepo.UserEntityRepo
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepoUseCase @Inject constructor(private val loginRepository: LoginRepository){

    fun login(userEntityRepo: UserEntityRepo) : Flow<String> = flow{
//        emit(joinRepository.signUp(joinEntitiyRepo))




    }


}