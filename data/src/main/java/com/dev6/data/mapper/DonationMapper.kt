package com.dev6.data.mapper

import com.dev6.data.model.PageResponse
import com.dev6.data.model.SortResponse
import com.dev6.data.entity.SortXX
import com.dev6.data.model.donation.DonationFeedResponse

import com.dev6.data.model.donation.DonationPaginationResponse
import com.dev6.domain.model.*
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.donate.DonationPostPage

internal fun DonationPaginationResponse?.toDomain() = DonationPostPage(
    donationFeedList = this?.donationFeedList?.map { it.toDomain() } ?: listOf(),
    empty = this?.empty ?: false,
    first = this?.first ?: false,
    last = this?.last ?: false,
    number = this?.number ?: 0,
    numberOfElements = this?.numberOfElements ?: 0,
    page = this?.page.toDomain(),
    size = this?.size ?: 0,
    sort = this?.sort.toDomain(),
    totalElements = this?.totalElements ?: 0,
    totalPages = this?.totalPages ?: 0
)

internal fun List<DonationFeedResponse>.toDomain(): List<DonationPost> {
    var temp: ArrayList<DonationPost> = ArrayList()
    this.forEach {
        temp.add(it.toDomain())
    }
    return temp
}


internal fun SortResponse?.toDomain() = Sort(
    empty = this?.empty ?: false,
    sorted = this?.sorted ?: false,
    unsorted = this?.unsorted ?: false
)


internal fun SortXX?.toXX() = Sort(
    empty = this?.empty ?: false,
    sorted = this?.sorted ?: false,
    unsorted = this?.unsorted ?: false
)

//SortXX
internal fun PageResponse?.toDomain() = Pageable(
    offset = this?.offset ?: 0,
    pageNumber = this?.pageNumber ?: 0,
    pageSize = this?.pageSize ?: 0,
    paged = this?.paged ?: false,
    sort = this?.sort.toDomain(),
    unpaged = this?.unpaged ?: false
)

internal fun DonationPostPage.toMapper() = DonationPaginationResponse(
    donationFeedList = this.donationFeedList as List<DonationFeedResponse>,
    empty = this.empty,
    first = this.first,
    last = this.last,
    number = this.number,
    numberOfElements = this.numberOfElements,
    page = this.page as PageResponse,
    size = this.size,
    sort = this.sort as SortResponse,
    totalElements = totalElements,
    totalPages = this.totalPages
)

internal fun DonationFeedResponse?.toDomain() = DonationPost(
    contents = this?.contents ?: "",
    donationMethod = this?.donationMethod ?: "",
    donationPostId = this?.donationPostId ?: "",
    endDate = this?.endDate ?: emptyList(),
    images = this?.images ?: emptyList(),
    startDate = this?.startDate ?: emptyList(),
    title = this?.title ?: "",
    userId = this?.userId ?: ""
)

internal fun DonationPostPage.toMakeViewLayer(temp: DonationFeedResponse) = DonationFeedResponse(
    contents = temp.contents,
    donationMethod = temp.donationMethod,
    donationPostId = temp.donationPostId,
    endDate = temp.endDate,
    images = temp.images,
    startDate = temp.startDate,
    title = temp.title,
    userId = temp.userId
)



