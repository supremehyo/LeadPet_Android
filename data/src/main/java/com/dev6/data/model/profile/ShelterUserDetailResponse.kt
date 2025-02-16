package com.dev6.data.model.profile

import com.google.gson.annotations.SerializedName

data class ShelterUserDetailResponse(
    @SerializedName("assessmentStatus")
    val assessmentStatus: String,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("shelterAccount")
    val shelterAccount: String,
    @SerializedName("shelterAddress")
    val shelterAddress: String,
    @SerializedName("shelterHomepage")
    val shelterHomepage: String,
    @SerializedName("shelterIntro")
    val shelterIntro: String,
    @SerializedName("shelterManager")
    val shelterManager: String,
    @SerializedName("shelterName")
    val shelterName: String,
    @SerializedName("shelterPhoneNumber")
    val shelterPhoneNumber: String,
    @SerializedName("userId")
    val userId: String
)
