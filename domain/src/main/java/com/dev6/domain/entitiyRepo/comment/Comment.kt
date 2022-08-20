package com.dev6.domain.entitiyRepo.comment

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("content")
    val content: String,
    @SerializedName("createdDate")
    val createdDate: List<String>,
    @SerializedName("normalReplyId")
    val normalReplyId: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    val userName: String
)
