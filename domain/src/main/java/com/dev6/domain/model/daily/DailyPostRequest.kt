package com.dev6.domain.model.daily

import com.google.gson.annotations.SerializedName

data class DailyPostRequest(
    @SerializedName("contents")
    val contents: String,
    @SerializedName("images")
    val images: List<String>?,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,

    val imageByteList : List<ByteArray>
)
