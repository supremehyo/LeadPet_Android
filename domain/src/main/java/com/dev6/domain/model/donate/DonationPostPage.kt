package com.dev6.domain.model.donate

import com.dev6.domain.model.Pageable
import com.dev6.domain.model.Sort

data class DonationPostPage(
    val donationFeedList: List<DonationPost>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val page: Pageable,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
)
