package com.dev6.data.model.saved

import com.google.gson.annotations.SerializedName

data class SavePageResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)
