package com.dev6.domain.model.donate

import com.dev6.core.enums.DonationMethod
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DonationPost(
    val contents: String,
    val donationMethod: DonationMethod,
    val donationPostId: String,
    val endDate: List<String>,
    val images: List<String>,
    val startDate: List<String>,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("profileImage")
    val profileImage: String,
    val title: String,
    val userId: String
) : Serializable
