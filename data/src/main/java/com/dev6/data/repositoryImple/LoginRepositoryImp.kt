package com.dev6.data.repositoryImple

import com.dev6.domain.entitiyRepo.LoginEntitiy
import com.dev6.domain.entitiyRepo.UserEntity
import com.dev6.data.remote.LoginRemoteSource
import com.dev6.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImp  @Inject constructor(private val loginRemoteSource: LoginRemoteSource) : LoginRepository{

    override suspend fun login(loginEntity: LoginEntitiy): Response<UserEntity> = loginRemoteSource.login(loginEntitiy = loginEntity)



}