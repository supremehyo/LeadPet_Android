package com.dev6.domain.model

import com.google.gson.annotations.SerializedName

data class ProfileUserUpdateRepo(
    @SerializedName("shelterAccount")
    val shelterAccount: String,
    @SerializedName("shelterAddress")
    val shelterAddress: String,
    @SerializedName("shelterHomePage")
    val shelterHomePage: String,
    @SerializedName("shelterIntro")
    val shelterIntro: String,
    @SerializedName("shelterManager")
    val shelterManager: String,
    @SerializedName("shelterName")
    val shelterName: String,
    @SerializedName("shelterPhoneNumber")
    val shelterPhoneNumber: String,

    val imageList : List<ByteArray>
)
