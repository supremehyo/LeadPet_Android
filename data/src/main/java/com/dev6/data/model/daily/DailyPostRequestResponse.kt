package com.dev6.data.model.daily

import com.google.gson.annotations.SerializedName

data class DailyPostRequestResponse (
    @SerializedName("contents")
    val contents: String,
    @SerializedName("images")
    val images: List<String>?,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String
)

