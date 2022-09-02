package com.dev6.data.mapper

import com.dev6.data.model.login.LoginRequest
import com.dev6.data.model.login.LoginResponse
import com.dev6.domain.model.Login
import com.dev6.domain.model.User

internal fun LoginRequest.toDomain() = Login(
    loginMethod = this.loginMethod,
    uid = this.uid,
    email = this.email,
    password = password,
    userType = this.userType
)

internal fun Login.toMapper() = LoginRequest(
    loginMethod = this.loginMethod,
    uid = this.uid,
    email = this.email,
    password = password,
    userType = this.userType
)

internal fun LoginResponse.toDomain() = User(
    uid = this.uid,
    profileImage = this.profileImage,
    userId = this.userId,
    userType = this.userType
)

internal fun LoginResponse.toMapper() = User(
    uid = this.uid,
    profileImage = this.profileImage,
    userId = this.userId,
    userType = this.userType
)
