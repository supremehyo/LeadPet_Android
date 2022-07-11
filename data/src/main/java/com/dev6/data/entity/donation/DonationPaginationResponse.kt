package com.dev6.data.entity.donation

import com.dev6.data.entity.PageEntitiy
import com.dev6.data.entity.SortEntitiy
import com.google.gson.annotations.SerializedName

data class DonationPaginationResponse(
    @SerializedName("content")
    val donationFeedEntitiyList: List<DonationFeedEntitiy>,
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
    val pageEntitiy: PageEntitiy,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val sortEntitiy: SortEntitiy,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int

)