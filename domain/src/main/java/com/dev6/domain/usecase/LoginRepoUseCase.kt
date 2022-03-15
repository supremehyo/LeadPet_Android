package com.dev6.domain.usecase

import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.LoginEntityRepo
import com.dev6.domain.entitiyRepo.UserEntityRepo
import com.dev6.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class LoginRepoUseCase @Inject constructor(private val loginRepository: LoginRepository){

    fun login(loginEntityRepo: LoginEntityRepo) : Flow<UiState<UserEntityRepo>> = flow{

        if (loginEntityRepo.email.isNullOrEmpty()) throw Exception()

        if(loginEntityRepo.password.isNullOrEmpty()) throw Exception()

        //todo 확장함수로 정규식 표현 만들기 이메일 형식이 올바르지 않은거 체크
//        if(loginEntityRepo.)

        //시작할때 로딩을 emit
        emit(UiState.Loding)
        val response = loginRepository.login(loginEntityRepo)
       if(response.isSuccessful){
           //Body가 없을경우, exception
           val userEntityRepo =  response.body() ?: throw Exception()


       }




    }


}