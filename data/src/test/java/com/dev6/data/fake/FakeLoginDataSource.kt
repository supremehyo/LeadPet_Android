/*
package com.dev6.data.fake

import com.dev6.data.remote.LoginRemoteSource
import com.dev6.domain.model.Login
import com.dev6.domain.model.User
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeLoginDataSource() : LoginRemoteSource {

    override suspend fun login(loginEntitiy: Login): Response<User> =
        if (loginEntitiy.email.isNullOrEmpty() || loginEntitiy.password.isNullOrEmpty()) {
            Response.error(400, "실패했습니다".toResponseBody())
        } else Response.success(User("테스트", "테스트"))
}
*/
