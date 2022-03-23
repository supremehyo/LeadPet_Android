package com.dev6.domain.entitiyRepo

interface  JoinEntitiyRepo {
    var _loginMethod: String
    var _uid : String
    var _email : String?
    var _password : String?
    var _profileImage : String?
    var _name : String
    var _shelterName : String
    var _shelterAddress : String
    var _shelterPhoneNumber : String
    var _shelterManager : String
    var _shelterHomePage : String
    var _userType : String
}