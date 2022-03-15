package com.dev6.data.entity

import com.dev6.domain.entitiyRepo.UserEntityRepo

data class UserEntitiy(
    override val type: String ,
    override val uid : String ,
    override val email : String?,
     override val password : String?
) : UserEntityRepo
