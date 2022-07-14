package com.dev6.data.model


import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.google.gson.annotations.SerializedName

data class JoinEntitiy(
    @SerializedName("loginMethod")
    val loginMethod: String,
    @SerializedName("uid")
    val uid : String,
    @SerializedName("email")
    val email : String?,
    @SerializedName("password")
    val password : String?,
    @SerializedName("profileImage")
    val profileImage : String?,
    @SerializedName("name")
    val name : String,
    @SerializedName("shelterName")
    val shelterName : String,
    @SerializedName("shelterAddress")
    val shelterAddress : String,
    @SerializedName("shelterPhoneNumber")
    val shelterPhoneNumber : String,
    @SerializedName("shelterManager")
    val shelterManager : String,
    @SerializedName("shelterHomePage")
    val shelterHomePage : String,
    @SerializedName("userType")
    val userType : String,
) : JoinEntitiyRepo{
    override var _loginMethod: String
        get() = loginMethod
        set(value) {}
    override var _uid: String
        get() = uid
        set(value) {}
    override var _email: String?
        get() = email
        set(value) {}
    override var _password: String?
        get() = password
        set(value) {}
    override var _profileImage: String?
        get() = profileImage
        set(value) {}
    override var _name: String
        get() = name
        set(value) {}
    override var _shelterName: String
        get() = shelterName
        set(value) {}
    override var _shelterAddress: String
        get() = shelterAddress
        set(value) {}
    override var _shelterPhoneNumber: String
        get() = shelterPhoneNumber
        set(value) {}
    override var _shelterManager: String
        get() = shelterManager
        set(value) {}
    override var _shelterHomePage: String
        get() = shelterHomePage
        set(value) {}
    override var _userType: String
        get() = userType
        set(value) {}


}
