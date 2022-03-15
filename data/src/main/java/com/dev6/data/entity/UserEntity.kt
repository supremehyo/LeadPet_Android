package com.dev6.data.entity

import com.dev6.domain.entitiyRepo.UserEntityRepo

data class UserEntity(
    override val uid: String,
    override val profileImage: String
    ): UserEntityRepo
