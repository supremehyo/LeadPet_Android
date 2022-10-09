package com.dev6.data.model.daily

import com.dev6.domain.model.Sort
import com.google.gson.annotations.SerializedName

data class DailyPageResponse(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("pageNumber")
    val pageNumber: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("paged")
    val paged: Boolean,
    @SerializedName("sort")
    val sort: Sort,
    @SerializedName("unpaged")
    val unpaged: Boolean
)