package com.dev6.data.model.daily

import com.google.gson.annotations.SerializedName

data class DailyPaginationResponse(
    @SerializedName("content")
    val dailyFeedEntitiy: List<DailyFeedEntitiy>,
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
    val dailyPageEntitiy: DailyPageEntitiy,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val dailySortEntitiy: DailySortEntitiy,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)