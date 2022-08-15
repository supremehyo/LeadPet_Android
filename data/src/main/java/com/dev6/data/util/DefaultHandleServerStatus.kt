package com.dev6.data.util

import com.dev6.core.util.extension.HandleServerResult
import com.dev6.core.util.extension.HandleServerStatus
import com.dev6.data.model.ErrorResponse

class DefaultHandleServerStatus(private val response: com.dev6.data.model.Error) : HandleServerStatus {

    override fun isSuccess(){

    }

    override fun isServerFail(): HandleServerResult =
        HandleServerResult(response.message, response.code == 404)

    override fun isNotAutority(): HandleServerResult =
        HandleServerResult(response.message, response.code == 403)

    override fun isAleadyExit(): HandleServerResult =
        HandleServerResult(response.message, response.code == 409)

    override fun isNotCorrect(): HandleServerResult =
        HandleServerResult(response.message, response.code == 400)

}