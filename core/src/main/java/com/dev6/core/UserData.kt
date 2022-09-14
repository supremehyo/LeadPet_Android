package com.dev6.core

import com.dev6.core.enums.LoginType
import com.dev6.core.enums.UserType

object UserData {
    var loginMethod: LoginType = LoginType.EMAIL
    var uid: String = ""
    var userId: String = ""
    var email: String? = ""
    var userType: UserType? = UserType.NORMAL
}
