package com.dev6.data.remote

import com.dev6.data.entity.JoinEntitiy
import com.dev6.data.entity.UserEntitiy
import com.dev6.data.service.JoinAPI
import com.dev6.data.service.LoginAPI
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.entitiyRepo.UserEntityRepo
import javax.inject.Inject

interface LoginRemoteSource {
    suspend fun login(userEntitiy: UserEntityRepo): String

}


class LoginRemoteSourceImpl @Inject constructor(
    private val loginService: LoginAPI
) : LoginRemoteSource {

    override suspend fun login(userEntitiy: UserEntityRepo): String {
        return loginService.login(userEntitiy)
    }


}