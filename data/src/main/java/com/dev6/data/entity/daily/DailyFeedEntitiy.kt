package com.dev6.data.entity.daily

import com.google.gson.annotations.SerializedName

data class DailyFeedEntitiy(
    @SerializedName("contents")
    val contents: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("normalPostId")
    val normalPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String
)