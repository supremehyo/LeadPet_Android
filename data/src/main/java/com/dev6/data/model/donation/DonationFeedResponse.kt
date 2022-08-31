package com.dev6.data.model.donation

import com.google.gson.annotations.SerializedName

data class DonationFeedResponse(
    @SerializedName("content")
    val contents: String,
    @SerializedName("donationMethod")
    val donationMethod: String,
    @SerializedName("donationPostId")
    val donationPostId: String,
    @SerializedName("endDate")
    val endDate: List<String>,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("startDate")
    val startDate: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String
)