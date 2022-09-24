package com.dev6.domain.model.comment

import com.google.gson.annotations.SerializedName

data class CommentUpdate(
    @SerializedName("content")
    val content: String,
    @SerializedName("normalPostId")
    val normalPostId: String,
    @SerializedName("userId")
    val userId: String,
)
