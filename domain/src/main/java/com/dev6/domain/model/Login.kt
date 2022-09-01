package com.dev6.domain.model

import com.dev6.core.enums.LoginType
import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("loginMethod")
    val loginMethod: LoginType,
    @SerializedName("uid")
    val uid: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password: String? = null
)



