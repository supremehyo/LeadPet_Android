package com.dev6.data.model.saved

import com.google.gson.annotations.SerializedName

data class SavedDailyRequestResponse(
    @SerializedName("contents")
    val contents: String,
    @SerializedName("createdDate")
    val createdDate: List<String>,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("normalPostId")
    val normalPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userId")
    val userId: String,
)