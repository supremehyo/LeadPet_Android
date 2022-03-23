package com.dev6.data.repositoryImple

import com.dev6.core.base.ApiResponse
import com.dev6.data.remote.LoginRemoteSource
import com.dev6.domain.entitiyRepo.LoginEntityRepo
import com.dev6.domain.entitiyRepo.UserEntityRepo
import com.dev6.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImp  @Inject constructor(private val loginRemoteSource: LoginRemoteSource) : LoginRepository{

    override suspend fun login(loginEntity: LoginEntityRepo): Response<UserEntityRepo> = loginRemoteSource.login(loginEntitiy = loginEntity)



}