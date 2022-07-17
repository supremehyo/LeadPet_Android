package com.dev6.data.model.donation

import com.dev6.data.model.PageEntitiy
import com.dev6.data.model.SortEntitiy
import com.google.gson.annotations.SerializedName

data class DonationPaginationResponse(
    @SerializedName("content")
    val donationFeedList: List<DonationFeedEntitiy>,
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
    val page: PageEntitiy,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val sort: SortEntitiy,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int

)