package com.dev6.domain.model

import com.google.gson.annotations.SerializedName

data class Join(
    @SerializedName("loginMethod")
    val loginMethod: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("shelterName")
    val shelterName: String,
    @SerializedName("shelterAddress")
    val shelterAddress: String,
    @SerializedName("shelterAccount")
    val shelterAccount : String,
    @SerializedName("shelterPhoneNumber")
    val shelterPhoneNumber: String,
    @SerializedName("shelterManager")
    val shelterManager: String,
    @SerializedName("shelterHomePage")
    val shelterHomePage: String,
    @SerializedName("userType")
    val userType: String,
    @SerializedName("shelterIntro")
    val shelterIntro: String,

    val imageList : List<ByteArray>
)