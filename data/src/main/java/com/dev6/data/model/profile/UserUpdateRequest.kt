package com.dev6.data.model.profile

import com.google.gson.annotations.SerializedName

data class UserUpdateRequest(
    @SerializedName("address")
    val address: String,
    @SerializedName("intro")
    val intro: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profileImage")
    val profileImage: String,
)
