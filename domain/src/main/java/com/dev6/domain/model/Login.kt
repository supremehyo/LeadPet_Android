package com.dev6.domain.model

import com.dev6.core.enums.LoginType
import com.dev6.core.enums.UserType
import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("loginMethod")
    val loginMethod: LoginType,
    @SerializedName("uid")
    val uid: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("userType")
    val userType: UserType = UserType.NORMAL
)
