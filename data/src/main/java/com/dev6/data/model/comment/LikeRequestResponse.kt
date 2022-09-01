package com.dev6.data.model.comment

import com.google.gson.annotations.SerializedName

data class LikeRequestResponse(
    @SerializedName("postId")
    val postId: String,
    @SerializedName("userId")
    val userId: String,
)
