package com.dev6.data.model

import com.google.gson.annotations.SerializedName

data class SortResponse(
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("sorted")
    val sorted: Boolean,
    @SerializedName("unsorted")
    val unsorted: Boolean
)
