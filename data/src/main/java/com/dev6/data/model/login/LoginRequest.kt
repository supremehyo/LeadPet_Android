package com.dev6.data.model.login

import com.dev6.core.enums.LoginType
import com.dev6.core.enums.UserType
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("loginMethod")
    val loginMethod: LoginType,
    @SerializedName("uid")
    val uid: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("password")
    val password: String? = null,

    val userType: UserType = UserType.NORMAL
)
