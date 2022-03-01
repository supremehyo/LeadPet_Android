package com.dev6.domain.entitiyRepo

interface  JoinEntitiyRepo {
    var type: String
    var uid : String
    var email : String?
    var password : String?
    var profileImage : String?
    var name : String
    var shelterName : String
    var shelterAddress : String
    var shelterPhoneNumber : String
    var shelterManager : String
    var shelterHomePage : String
}