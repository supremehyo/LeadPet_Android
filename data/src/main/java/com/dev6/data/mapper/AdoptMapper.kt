package com.dev6.data.mapper

import com.dev6.data.entity.PageEntitiy
import com.dev6.data.entity.SortEntitiy
import com.dev6.data.entity.SortXX
import com.dev6.data.entity.adopt.AdoptFeedEntitiy
import com.dev6.data.entity.adopt.AdoptPageEntitiy
import com.dev6.data.entity.adopt.AdoptPaginationResponse
import com.dev6.data.entity.adopt.AdoptSortEntitiy
import com.dev6.data.entity.donation.DonationFeedEntitiy
import com.dev6.data.entity.donation.DonationPaginationResponse
import com.dev6.domain.entitiyRepo.*
import com.dev6.domain.entitiyRepo.adopt.AdoptPost
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed

internal fun AdoptPaginationResponse?.toDomain() = AdoptPost(
    adoptPostFeed = this?.adoptFeedEntitiyList?.toDomain() ?: emptyList(),
    empty = this?.empty ?: false,
    first = this?.first ?: false,
    last = this?.last ?: false,
    number = this?.number ?: 0,
    numberOfElements = this?.numberOfElements ?: 0,
    pageable = this?.adoptPageEntitiy.toDomain(),
    size = this?.size ?: 0,
    sort = this?.adoptSortEntitiy.toDomain(),
    totalElements = this?.totalElements ?: 0,
    totalPages = this?.totalPages ?: 0
)

internal fun AdoptSortEntitiy?.toDomain() = Sort(
    empty = this?.empty ?: false,
    sorted = this?.sorted ?: false,
    unsorted = this?.unsorted ?: false
)
internal fun AdoptPageEntitiy?.toDomain() = Page(
    page = this?.page ?: 0,
    size = this?.size ?: 0
)


internal fun List<AdoptFeedEntitiy>.toDomain(): List<AdoptPostFeed> {
    var temp: ArrayList<AdoptPostFeed> = ArrayList()
    this.forEach {
        temp.add(it.toData())
    }
    return temp
}

internal fun AdoptFeedEntitiy.toData() = AdoptPostFeed(
    contents = contents,
    animalType = animalType,
    adoptionPostId = adoptionPostId,
    endDate = endDate,
    images = images,
    startDate = startDate,
    title = title,
    userId = userId,
    neutering = neutering,
    species = species,
    gender = gender,
    euthanasiaDate = euthanasiaDate
)
