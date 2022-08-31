package com.dev6.domain.model.donate

import com.dev6.core.enum.DonationMethod
import java.io.Serializable

data class DonationPost(
    val contents: String,
    val donationMethod: DonationMethod,
    val donationPostId: String,
    val endDate: List<String>,
    val images: List<String>,
    val startDate: List<String>,
    val title: String,
    val userId: String
) : Serializable
