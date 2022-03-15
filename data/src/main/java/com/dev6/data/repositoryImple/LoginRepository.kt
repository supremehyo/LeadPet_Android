package com.dev6.data.repositoryImple

import com.dev6.data.remote.JoinRemoteSource
import com.dev6.data.remote.LoginRemoteSource
import com.dev6.domain.entitiyRepo.UserEntityRepo
import com.dev6.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImple  @Inject constructor(private val loginRemoteSource: LoginRemoteSource) : LoginRepository{

    override suspend fun login(userEntity: UserEntityRepo): String = loginRemoteSource.login(userEntitiy = userEntity)



}