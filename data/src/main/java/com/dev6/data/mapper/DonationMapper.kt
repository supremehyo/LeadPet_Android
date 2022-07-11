package com.dev6.data.mapper

import com.dev6.data.entity.PageEntitiy
import com.dev6.data.entity.SortEntitiy
import com.dev6.data.entity.donation.DonationFeedEntitiy

import com.dev6.data.entity.donation.DonationPaginationResponse
import com.dev6.domain.entitiyRepo.*

internal fun DonationPaginationResponse?.toDomain() = DonationPost(
    donationFeedList = this?.donationFeedEntitiyList as List<DonationPostFeed>,
    empty = this?.empty ?: false,
    first = this?.first ?: false,
    last = this?.last ?: false,
    number = this?.number ?: 0,
    numberOfElements = this?.numberOfElements ?: 0,
    page = this?.pageEntitiy as Pageable,
    size = this?.size ?: 0,
    sort = this?.sortEntitiy as Sort,
    totalElements = totalElements,
    totalPages = this.totalPages
)

internal fun DonationPost.toMapper() = DonationPaginationResponse(
    donationFeedEntitiyList =this.donationFeedList as List<DonationFeedEntitiy>,
    empty = this.empty,
    first = this.first,
    last = this.last,
    number = this.number,
    numberOfElements = this.numberOfElements,
    pageEntitiy = page as PageEntitiy,
    size = this.size,
    sortEntitiy = sort as SortEntitiy,
    totalElements = totalElements,
    totalPages = this.totalPages
)

internal fun DonationFeedEntitiy.toData() = DonationPostFeed(
    contents = contents,
    donationMethod = donationMethod,
    donationPostId = donationPostId,
    endDate = endDate,
    images = images,
    startDate =startDate,
    title = title,
    userId = userId
)

internal fun DonationPost.toMakeViewLayer(temp: DonationFeedEntitiy) = DonationFeedEntitiy(
    contents = temp.contents,
    donationMethod = temp.donationMethod,
    donationPostId = temp.donationPostId,
    endDate = temp.endDate,
    images = temp.images,
    startDate = temp.startDate,
    title = temp.title,
    userId = temp.userId
)




