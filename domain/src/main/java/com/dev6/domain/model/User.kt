package com.dev6.domain.model

import com.dev6.core.enums.UserType

data class User(
    val uid: String,
    val profileImage: String,
    val userId: String,
    val userType: UserType
)
