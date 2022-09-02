package com.dev6.data.model.login

import com.dev6.core.enums.UserType

data class LoginResponse(
    val uid: String,
    val profileImage: String,
    val userId: String,
    val userType: UserType
)
