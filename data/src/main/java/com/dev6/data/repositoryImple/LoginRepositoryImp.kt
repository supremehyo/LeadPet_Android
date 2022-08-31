package com.dev6.data.repositoryImple

import com.dev6.data.remote.LoginRemoteSource
import com.dev6.domain.model.Login
import com.dev6.domain.model.User
import com.dev6.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(private val loginRemoteSource: LoginRemoteSource) :
    LoginRepository {

    override suspend fun login(login: Login): Response<User> =
        loginRemoteSource.login(loginEntitiy = login)
}
