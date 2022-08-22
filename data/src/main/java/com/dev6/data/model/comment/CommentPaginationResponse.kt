package com.dev6.data.model.comment

import com.dev6.data.model.daily.DailyPageEntitiy
import com.dev6.data.model.daily.DailySortEntitiy
import com.google.gson.annotations.SerializedName

data class CommentPaginationResponse(
    @SerializedName("content")
    val commentEntitiy: List<CommentEntitiy>,
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("first")
    val first: Boolean,
    @SerializedName("last")
    val last: Boolean,
    @SerializedName("number")
    val number: Int,
    @SerializedName("numberOfElements")
    val numberOfElements: Int,
    @SerializedName("pageable")
    val commentPageEntitiy: CommentPageEntitiy,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val commentSortEntitiy: CommentSortEntitiy,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)