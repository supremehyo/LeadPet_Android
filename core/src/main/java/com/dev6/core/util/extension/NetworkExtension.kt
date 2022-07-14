package com.jydev.rest_api_util.extension

import com.dev6.core.exception.*
import com.dev6.core.util.extension.HandleServerStatus

suspend fun <DATA> DATA.executeErrorHandling(
    handleServerStatus: HandleServerStatus? = null
): DATA {
    handleServerStatus?.let {
        val serverFailResult = handleServerStatus.isServerFail()
        val alreadyExit = handleServerStatus.isAleadyExit()
        val notAutorutyResult = handleServerStatus.isNotAutority()
        val notCorrect = handleServerStatus.isNotCorrect()
        return when {
            serverFailResult.status -> {
                throw ServerFailException(serverFailResult.message)
            }
            notAutorutyResult.status -> {
                throw NotAutorityException(notAutorutyResult.message)
            }
            alreadyExit.status -> {
                throw AleadyExitException(alreadyExit.message)
            }
            notCorrect.status -> {
                throw NotCorrectException(notCorrect.message)
            }
            else -> this
        }
    } ?: kotlin.run {
        return this
    }
}

//suspend fun <DATA> executeChangeResult(getData: suspend () -> DATA): NetworkResult<DATA> {
//    return try {
//        val data = getData()
//        NetworkResult.Success(data)
//    } catch (e: Exception) {
//        when (e) {
//            is IdleException -> NetworkResult.Idle(e.message)
//            is ServerErrorException -> NetworkResult.ServerError(e.message)
//            is ServerFailException -> NetworkResult.ServerFail(e.message)
//            else -> NetworkResult.Exception(e)
//        }
//    }
//}
