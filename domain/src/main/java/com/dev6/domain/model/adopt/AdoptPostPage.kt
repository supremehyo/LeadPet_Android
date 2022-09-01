package com.dev6.domain.model.adopt

import com.dev6.domain.model.Page
import com.dev6.domain.model.Sort

data class AdoptPostPage(
    val adoptPostFeed: List<AdoptPostFeed>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Page,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
)