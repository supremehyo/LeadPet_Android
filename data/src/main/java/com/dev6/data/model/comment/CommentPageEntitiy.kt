package com.dev6.data.model.comment

import com.google.gson.annotations.SerializedName

data class CommentPageEntitiy(
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)
