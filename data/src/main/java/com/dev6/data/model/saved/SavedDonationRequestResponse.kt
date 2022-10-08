package com.dev6.data.model.saved

import com.google.gson.annotations.SerializedName

data class SavedDonationRequestResponse(
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("donationPostId")
    val donationPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,
)
