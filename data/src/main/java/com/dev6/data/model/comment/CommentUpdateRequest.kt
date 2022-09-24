package com.dev6.data.model.comment

import com.google.gson.annotations.SerializedName

data class CommentUpdateRequest(
    @SerializedName("content")
    val content: String,
    @SerializedName("normalPostId")
    val normalPostId: String,
    @SerializedName("userId")
    val userId: String,
)
