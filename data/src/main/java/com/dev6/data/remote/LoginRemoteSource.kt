package com.dev6.data.remote

import com.dev6.data.service.LoginAPI
import com.dev6.domain.entitiyRepo.LoginEntityRepo
import com.dev6.domain.entitiyRepo.UserEntityRepo
import retrofit2.Response
import javax.inject.Inject

interface LoginRemoteSource {
    suspend fun login(loginEntitiy: LoginEntityRepo): Response<UserEntityRepo>

}


class LoginRemoteSourceImpl @Inject constructor(
    private val loginService: LoginAPI
) : LoginRemoteSource {

    override suspend fun login(loginEntitiy: LoginEntityRepo): Response<UserEntityRepo> {
        return loginService.login(loginEntitiy)
    }


}