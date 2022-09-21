package com.dev6.core

import com.dev6.core.enums.LoginType
import com.dev6.core.enums.UserType

object UserData {
    var loginMethod: LoginType = LoginType.EMAIL
    var uid: String = ""
    var userId: String = ""
    var userName: String = ""
    var email: String? = ""
    var userType: UserType? = UserType.NORMAL
    var userCity : String? = ""
    var intro: String? = ""

    var profileImage : String? = ""
    var shelterAccount: String? = ""
    var shelterAddress: String? = ""
    var shelterHomepage: String? = ""
    var shelterIntro: String? = ""
    var shelterManager: String? = ""
    var shelterName: String? = ""
    var shelterPhoneNumber: String? = ""

}
