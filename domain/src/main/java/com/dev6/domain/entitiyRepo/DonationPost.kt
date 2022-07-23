package com.dev6.domain.entitiyRepo

data class DonationPost(
    val donationFeedList: List<DonationPostFeed>,
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
