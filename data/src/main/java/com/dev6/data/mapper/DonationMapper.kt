package com.dev6.data.mapper

import com.dev6.data.model.PageEntitiy
import com.dev6.data.model.SortEntitiy
import com.dev6.data.entity.SortXX
import com.dev6.data.model.donation.DonationFeedEntitiy

import com.dev6.data.model.donation.DonationPaginationResponse
import com.dev6.domain.entitiyRepo.*


internal fun DonationPaginationResponse?.toDomain() = DonationPost(
    donationFeedList = this?.donationFeedList?.toDomain() ?: emptyList(),
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

internal fun List<DonationFeedEntitiy>.toDomain(): List<DonationPostFeed> {
    var temp: ArrayList<DonationPostFeed> = ArrayList()
    this.forEach {
        temp.add(it.toData())
    }
    return temp
}


internal fun SortEntitiy?.toDomain() = Sort(
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
internal fun PageEntitiy?.toDomain() = Pageable(
    offset = this?.offset ?: 0,
    pageNumber = this?.pageNumber ?: 0,
    pageSize = this?.pageSize ?: 0,
    paged = this?.paged ?: false,
    sort = this?.sort.toDomain(),
    unpaged = this?.unpaged ?: false
)

internal fun DonationPost.toMapper() = DonationPaginationResponse(
    donationFeedList = this.donationFeedList as List<DonationFeedEntitiy>,
    empty = this.empty,
    first = this.first,
    last = this.last,
    number = this.number,
    numberOfElements = this.numberOfElements,
    page = this.page as PageEntitiy,
    size = this.size,
    sort = this.sort as SortEntitiy,
    totalElements = totalElements,
    totalPages = this.totalPages
)

internal fun DonationFeedEntitiy?.toData() = DonationPostFeed(
    contents = this?.contents ?: "",
    donationMethod = this?.donationMethod ?: "",
    donationPostId = this?.donationPostId ?: "",
    endDate = this?.endDate ?: emptyList(),
    images = this?.images ?: emptyList(),
    startDate = this?.startDate ?: emptyList(),
    title = this?.title ?: "",
    userId = this?.userId ?: ""
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



