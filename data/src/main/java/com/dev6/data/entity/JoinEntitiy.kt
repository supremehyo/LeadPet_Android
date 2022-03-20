package com.dev6.data.entity

import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JoinEntitiy(
    @SerializedName("email")
    val email : String?,
    @SerializedName("name")
    val name : String,
    @SerializedName("password")
    val password : String?,
    @SerializedName("profileImage")
    val profileImage : String?,
    @SerializedName("shelterAddress")
    val shelterAddress : String,
    @SerializedName("shelterHomePage")
    val shelterHomePage : String,
    @SerializedName("shelterManager")
    val shelterManager : String,
    @SerializedName("shelterName")
    val shelterName : String,
    @SerializedName("shelterPhoneNumber")
    val shelterPhoneNumber : String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uid")
    val uid : String,
    @SerializedName("userType")
    val userType : String
) : JoinEntitiyRepo , Serializable{
    override var _type: String
        get() = type
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
