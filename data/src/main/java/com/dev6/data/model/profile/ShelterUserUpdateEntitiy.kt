package com.dev6.data.model.profile

import com.google.gson.annotations.SerializedName

data class ShelterUserUpdateEntitiy(
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
)
