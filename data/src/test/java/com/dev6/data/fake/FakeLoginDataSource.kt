package com.dev6.data.fake

import com.dev6.data.remote.LoginRemoteSource
import com.dev6.domain.entitiyRepo.LoginEntity
import com.dev6.domain.entitiyRepo.UserEntity
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeLoginDataSource() : LoginRemoteSource {

    override suspend fun login(loginEntitiy: LoginEntity): Response<UserEntity> =
        if (loginEntitiy.email.isNullOrEmpty() || loginEntitiy.password.isNullOrEmpty())
            Response.error(400, "실패했습니다".toResponseBody())
        else Response.success(UserEntity("테스트", "테스트"))

}
