package com.dev6.data.model.profile

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("allReplyCount")
    val allReplyCount: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("intro")
    val intro: String,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    val userName: String,

)
