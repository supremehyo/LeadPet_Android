package com.dev6.data.repositoryImp

import com.dev6.data.mapper.toDomain
import com.dev6.data.mapper.toMapper
import com.dev6.data.remote.LoginRemoteSource
import com.dev6.domain.model.Login
import com.dev6.domain.model.User
import com.dev6.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(private val loginRemoteSource: LoginRemoteSource) : LoginRepository {
    override suspend fun login(login: Login): User = loginRemoteSource.login(login.toMapper()).toDomain()
}
