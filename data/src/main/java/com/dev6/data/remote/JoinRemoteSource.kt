package com.dev6.data.remote


import com.dev6.data.model.JoinResponse
import com.dev6.data.service.JoinAPI
import javax.inject.Inject

//E
interface JoinRemoteSource {
    suspend fun signUp(joinEntitiy: JoinResponse): String
}

class JoinRemoteSourceImpl @Inject constructor(
    private val joinService: JoinAPI
) : JoinRemoteSource {

    override suspend fun signUp(joinEntitiy: JoinResponse): String {
        return joinService.signUp(joinEntitiy)
    }
}