package com.dev6.domain.entitiyRepo

import com.dev6.core.enum.DonationMethod

interface DonatePostEntityRepo {
    val donationPostId: String
    val userId: String
    val startDate: Int
    val endDate: Int
    val title: String
    val donationMethod: DonationMethod
    val contents: String
    val images: List<String>
}

