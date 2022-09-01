package com.dev6.data.model.daily

import com.google.gson.annotations.SerializedName

data class DailyPaginationResponse(
    @SerializedName("content")
    val dailyFeedEntitiy: List<DailyFeedRequestResponse>,
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
    val dailyPageEntitiy: DailyPageResponse,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val dailySortEntitiy: DailySortResponse,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)