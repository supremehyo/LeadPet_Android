package com.dev6.domain.usecase

import com.dev6.domain.entitiyRepo.LoginEntityRepo
import com.dev6.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepoUseCase @Inject constructor(private val loginRepository: LoginRepository){

    fun login(loginEntityRepo: LoginEntityRepo) : Flow<Ui> = flow{
//        emit(joinRepository.signUp(joinEntitiyRepo))
        val response = loginRepository.login(loginEntityRepo)
       if(response.isSuccessful){
           emit(response.body()
       }




    }


}