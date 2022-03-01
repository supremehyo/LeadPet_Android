package com.dev6.data.entity

import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.google.gson.annotations.SerializedName

data class JoinEntitiy(
    @SerializedName("type")
    var _type: String,
    @SerializedName("uid")
    var _uid : String,
    @SerializedName("email")
    var _email : String?,
    @SerializedName("password")
    var _password : String?,
    @SerializedName("profileImage")
    var _profileImage : String?,
    @SerializedName("name")
    var _name : String,
    @SerializedName("shelterName")
    var _shelterName : String,
    @SerializedName("shelterAddress")
    var _shelterAddress : String,
    @SerializedName("shelterPhoneNumber")
    var _shelterPhoneNumber : String,
    @SerializedName("shelterManager")
    var _shelterManager : String,
    @SerializedName("shelterHomePage")
    var _shelterHomePage : String,
) : JoinEntitiyRepo {
    override var type: String
        get() = _type
        set(value) {type = value}
    override var uid: String
        get() = _uid
        set(value) {}
    override var email: String?
        get() = _email
        set(value) {}
    override var password: String?
        get() = _password
        set(value) {}
    override var profileImage: String?
        get() = _profileImage
        set(value) {}
    override var name: String
        get() = _name
        set(value) {}
    override var shelterName: String
        get() = _shelterName
        set(value) {}
    override var shelterAddress: String
        get() = _shelterAddress
        set(value) {}
    override var shelterPhoneNumber: String
        get() = _shelterPhoneNumber
        set(value) {}
    override var shelterManager: String
        get() = _shelterManager
        set(value) {}
    override var shelterHomePage: String
        get() = _shelterHomePage
        set(value) {}

}
