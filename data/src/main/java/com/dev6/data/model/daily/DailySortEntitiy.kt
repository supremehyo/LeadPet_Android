package com.dev6.data.entity.daily

import com.google.gson.annotations.SerializedName

data class DailySortEntitiy(
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("sorted")
    val sorted: Boolean,
    @SerializedName("unsorted")
    val unsorted: Boolean
)