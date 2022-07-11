package com.dev6.domain.entitiyRepo

data class DonationPostFeed(
    val contents: String,
    val donationMethod: String,
    val donationPostId: String,
    val endDate: List<String>,
    val images: List<String>,
    val startDate: List<String>,
    val title: String,
    val userId: String
)
