package com.dev6.domain.model.save

import com.dev6.domain.model.Page
import com.dev6.domain.model.Sort
import com.google.gson.annotations.SerializedName

data class SavedDaily(
    @SerializedName("content")
    val savedDailyRequestResponse: List<SavedDailyData>,
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
    val sort: Sort,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)
