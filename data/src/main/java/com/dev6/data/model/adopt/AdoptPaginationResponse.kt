package com.dev6.data.entity.adopt

import com.google.gson.annotations.SerializedName

data class AdoptPaginationResponse(
    @SerializedName("content")
    val adoptFeedEntitiyList: List<AdoptFeedEntitiy>,
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("first")
    val first: Boolean,
    @SerializedName("last")
    val last: Boolean,
    @SerializedName("number")
    val number: Int,
    @SerializedName("numberOfElements")
    val numberOfElements: Int,
    @SerializedName("pageable")
    val adoptPageEntitiy: AdoptPageEntitiy,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val adoptSortEntitiy: AdoptSortEntitiy,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)