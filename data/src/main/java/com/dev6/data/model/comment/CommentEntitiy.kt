package com.dev6.data.model.comment

import com.google.gson.annotations.SerializedName

data class CommentEntitiy(
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