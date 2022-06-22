package com.dev6.data.repositoryImple

import com.dev6.data.remote.JoinRemoteSource
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.repository.JoinRepository
import javax.inject.Inject

class JoinRepositoryImple @Inject constructor(private val joinRemoteSource: JoinRemoteSource ) : JoinRepository {
    override suspend fun signUp(userEntitiy: JoinEntitiyRepo): String {
        return joinRemoteSource.signUp(userEntitiy) // 수정필요 as Entity로 캐스팅 필요
    }

}