package com.dev6.data.entity

import com.dev6.core.enum.LoginType
import com.dev6.domain.entitiyRepo.LoginEntityRepo
import com.google.gson.annotations.SerializedName


data class LoginEntitiy(
    @SerializedName("type")
    override var type: LoginType,
    @SerializedName("uid")
    override var uid: String? = null,
    @SerializedName("email")
    override var email: String? = null,
    @SerializedName("password")
    override var password: String? =null
) : LoginEntityRepo {


}



