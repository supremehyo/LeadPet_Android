package com.dev6.domain.model.daily

import com.dev6.domain.model.Page
import com.dev6.domain.model.Pageable
import com.dev6.domain.model.Sort
import com.google.gson.annotations.SerializedName

data class DailyPostPage(
    @SerializedName("content")
    val dailyFeedEntitiy: List<DailyPost>,
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
    val pageable: Pageable,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val dailySortEntitiy: Sort,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)