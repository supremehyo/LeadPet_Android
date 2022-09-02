package com.dev6.data.repositoryImple


import com.dev6.data.mapper.toMapper
import com.dev6.data.remote.JoinRemoteSource
import com.dev6.domain.model.Join
import com.dev6.domain.repository.JoinRepository
import javax.inject.Inject

class JoinRepositoryImp @Inject constructor(private val joinRemoteSource: JoinRemoteSource) :
    JoinRepository {
    override suspend fun signUp(userEntitiy: Join): String =
        joinRemoteSource.signUp(userEntitiy.toMapper())


}