package com.dev6.domain.model.comment

import com.dev6.domain.model.Page
import com.dev6.domain.model.Sort
import com.google.gson.annotations.SerializedName

data class CommentPage(
    @SerializedName("content")
    val commentEntitiy: List<Comment>,
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
    val commentPageEntitiy: Page,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val commentSortEntitiy: Sort,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)
