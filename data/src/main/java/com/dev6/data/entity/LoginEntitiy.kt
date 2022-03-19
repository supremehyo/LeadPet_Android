package com.dev6.data.entity

import com.dev6.core.enum.LoginType
import com.dev6.domain.entitiyRepo.LoginEntityRepo


data class LoginEntitiy(
    override var type: LoginType,
    override var uid: String? = null,
    override var email: String? = null,
    override var password: String? =null
) : LoginEntityRepo {


}



