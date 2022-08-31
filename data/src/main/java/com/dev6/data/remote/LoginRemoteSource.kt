package com.dev6.data.remote

import com.dev6.domain.model.Login
import com.dev6.domain.model.User
import com.dev6.data.service.LoginAPI
import retrofit2.Response
import javax.inject.Inject


/**
 *  Api랑 매핑 하는곳
 */
interface LoginRemoteSource {
    suspend fun login(loginEntitiy: Login): Response<User>

}


class LoginRemoteSourceImpl @Inject constructor(
    private val loginService: LoginAPI
) : LoginRemoteSource {

    override suspend fun login(loginEntitiy: Login): Response<User> {
        return loginService.login(loginEntitiy)
    }


}