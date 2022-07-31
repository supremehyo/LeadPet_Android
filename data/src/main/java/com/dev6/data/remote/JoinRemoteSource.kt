package com.dev6.data.remote


import com.dev6.data.model.JoinEntitiy
import com.dev6.data.service.JoinAPI
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import javax.inject.Inject

//E
interface JoinRemoteSource {
    suspend fun signUp(joinEntitiy: JoinEntitiy): String
}

class JoinRemoteSourceImpl @Inject constructor(
    private val joinService: JoinAPI
) : JoinRemoteSource {

    override suspend fun signUp(joinEntitiy: JoinEntitiy): String {
        return joinService.signUp(joinEntitiy)
    }
}