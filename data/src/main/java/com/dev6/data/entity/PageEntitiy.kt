package com.dev6.data.entity

import com.google.gson.annotations.SerializedName


data class PageEntitiy(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("pageNumber")
    val pageNumber: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("paged")
    val paged: Boolean,
    @SerializedName("sort")
    val sort: SortXX,
    @SerializedName("unpaged")
    val unpaged: Boolean

)