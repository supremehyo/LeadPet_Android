package com.dev6.domain.model.save

import com.google.gson.annotations.SerializedName

data class SavedDonationData(
    @SerializedName("images")
    val images: List<String>?,
    @SerializedName("donationPostId")
    val donationPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,
)
