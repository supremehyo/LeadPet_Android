package com.dev6.data.model.adopt

import com.google.gson.annotations.SerializedName

data class AdoptPageResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)