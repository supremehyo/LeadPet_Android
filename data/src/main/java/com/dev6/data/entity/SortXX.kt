package com.dev6.data.entity

import com.google.gson.annotations.SerializedName

data class SortXX(
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("sorted")
    val sorted: Boolean,
    @SerializedName("unsorted")
    val unsorted: Boolean
)