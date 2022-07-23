package com.dev6.domain.entitiyRepo.daily

import com.dev6.domain.entitiyRepo.Page
import com.dev6.domain.entitiyRepo.Sort
import com.google.gson.annotations.SerializedName

data class DailyPost(
    @SerializedName("content")
    val dailyFeedEntitiy: List<DailyPostFeed>,
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
    val pageable: Page,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val dailySortEntitiy: Sort,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)