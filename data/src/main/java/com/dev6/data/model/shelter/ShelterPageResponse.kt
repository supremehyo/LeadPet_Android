package com.dev6.data.model.shelter

import com.google.gson.annotations.SerializedName

data class ShelterPageResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)