package com.dev6.data.entity.adopt

import com.google.gson.annotations.SerializedName

data class AdoptSortEntitiy(
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("sorted")
    val sorted: Boolean,
    @SerializedName("unsorted")
    val unsorted: Boolean
)