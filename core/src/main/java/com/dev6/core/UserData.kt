package com.dev6.core

import com.dev6.core.enums.LoginType


 object UserData{
    var loginMethod: LoginType = LoginType.EMAIL
    var uid: String = ""
     var userId: String = ""
    var email: String? = ""
    var password: String? = ""
     var userType : String? = "NORMAL"
     var profileImage : String? = ""
}