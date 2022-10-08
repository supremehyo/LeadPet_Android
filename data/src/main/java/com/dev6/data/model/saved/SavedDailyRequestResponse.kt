package com.dev6.data.model.saved

import com.google.gson.annotations.SerializedName

data class SavedDailyRequestResponse(
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("normalPostId")
    val normalPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,
)