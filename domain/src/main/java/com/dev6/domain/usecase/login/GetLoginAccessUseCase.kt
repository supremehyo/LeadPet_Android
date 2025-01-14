package com.dev6.domain.usecase.login

import com.dev6.core.base.UiState
import com.dev6.domain.model.Login
import com.dev6.domain.model.User
import com.dev6.domain.repository.LoginRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias LoginAccessBaseUseCase = BaseUseCase<Login, Flow<UiState<User>>>

class GetLoginAccessUseCase @Inject constructor(private val loginRepository: LoginRepository) : LoginAccessBaseUseCase {

    //    fun login(loginRepo: Login): Flow<UiState<User>> = flow {
//        if (loginRepo.loginMethod == LoginType.EMAIL) {
//            if (loginRepo.email.isNullOrEmpty()) throw Exception()
//            if (loginRepo.password.isNullOrEmpty()) throw Exception()
//        }
//
//        // todo 확장함수로 정규식 표현 만들기 이메일 형식이 올바르지 않은거 체크
//
//        // 시작할때 로딩을 emit
//        emit(UiState.Loding)
//
//        val response = loginRepository.login(loginRepo)
//        if (response.isSuccessful) {
//            // Body가 없을경우, exception
//            val userEntityRepo = response.body() ?: throw Exception("사용자 값을 반환하지 않았습니다.")
//            emit(UiState.Success(userEntityRepo))
//        } else {
//            when (response.code()) {
//                404 -> {
//                    emit(UiState.Error(throw ServerFailException(response.message())))
//                }
//            }
//        }
//    }.catch { error ->
//        emit(UiState.Error(error))
//        Timber.d("에러에염 $error")
//    }
//
//    override suspend fun invoke(params: Login): Flow<UiState<User>> {
//        TODO("Not yet implemented")
//    }
    override suspend fun invoke(login: Login): Flow<UiState<User>> = flow {
        runCatching {
            loginRepository.login(login)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
