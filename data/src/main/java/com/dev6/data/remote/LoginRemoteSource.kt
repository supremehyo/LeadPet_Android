package com.dev6.data.remote

import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.model.login.LoginRequest
import com.dev6.data.model.login.LoginResponse
import com.dev6.data.service.LoginAPI
import javax.inject.Inject

/**
 *  Api랑 매핑 하는곳
 */
interface LoginRemoteSource {
    suspend fun login(loginEntitiy: LoginRequest): LoginResponse
}

class LoginRemoteSourceImpl @Inject constructor(
    private val loginService: LoginAPI
) : LoginRemoteSource {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse =
        loginService.login(loginRequest).executeNetworkHandling()
}
