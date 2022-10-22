package com.dev6.domain.model

import com.google.gson.annotations.SerializedName

data class NormalUserUpdateRepo(
    @SerializedName("address")
    val address: String,
    @SerializedName("intro")
    val intro: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profileImage")
    val profileImage: String,
    val imageByte : ByteArray
)
