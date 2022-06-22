package com.dev6.data.remote

import com.dev6.domain.entitiyRepo.LoginEntity
import com.dev6.domain.entitiyRepo.UserEntity
import com.dev6.data.service.LoginAPI
import retrofit2.Response
import javax.inject.Inject


/**
 *  Api랑 매핑 하는곳
 */
interface LoginRemoteSource {
    suspend fun login(loginEntitiy: LoginEntity): Response<UserEntity>

}


class LoginRemoteSourceImpl @Inject constructor(
    private val loginService: LoginAPI
) : LoginRemoteSource {

    override suspend fun login(loginEntitiy: LoginEntity): Response<UserEntity> {
        return loginService.login(loginEntitiy)
    }


}