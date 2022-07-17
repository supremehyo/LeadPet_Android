package com.dev6.data.entity.adopt

import com.google.gson.annotations.SerializedName

data class AdoptPageEntitiy(
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)