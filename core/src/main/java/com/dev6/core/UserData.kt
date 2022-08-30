package com.dev6.core

import com.dev6.core.enums.LoginType

object UserData {
    var loginMethod: LoginType = LoginType.EMAIL
    var uid: String = ""
    var email: String? = ""
    var password: String? = ""
}
