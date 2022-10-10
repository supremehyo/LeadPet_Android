package com.dev6.domain.model.save

import com.dev6.core.enums.DonationMethod
import com.google.gson.annotations.SerializedName

data class SavedDonationData(
    @SerializedName("content")
    val contents: String,
    @SerializedName("donationMethod")
    val donationMethod: DonationMethod,
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
    @SerializedName("userName")
    val userName: String,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("userId")
    val userId: String
    /*
    @SerializedName("images")
    val images: List<String>?,
    @SerializedName("donationPostId")
    val donationPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,

     */
)
