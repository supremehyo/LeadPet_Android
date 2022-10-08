package com.dev6.domain.model.save

import com.google.gson.annotations.SerializedName

data class SavedDailyData(
    @SerializedName("images")
    val images: List<String>?,
    @SerializedName("normalPostId")
    val normalPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,
)
