package com.dev6.data.model.daily

import com.google.gson.annotations.SerializedName

data class DailyFeedRequestResponse(
    @SerializedName("contents")
    val contents: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("normalPostId")
    val normalPostId: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("likedCount")
    val likedCount: Int,
    @SerializedName("createdDate")
    val createdDate: List<String>?,
    @SerializedName("commentCount")
    val commentCount: Int?,
    @SerializedName("liked")
    val liked: Boolean?
)
