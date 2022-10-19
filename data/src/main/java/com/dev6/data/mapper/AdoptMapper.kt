package com.dev6.data.mapper // ktlint-disable filename

import com.dev6.core.enum.AnimalType
import com.dev6.core.enum.Gender
import com.dev6.core.enum.Neutering
import com.dev6.data.entity.adopt.AdoptSortResponse
import com.dev6.data.model.adopt.AdoptFeedRequestResponse
import com.dev6.data.model.adopt.AdoptPageResponse
import com.dev6.data.model.adopt.AdoptPaginationResponse
import com.dev6.domain.model.Page
import com.dev6.domain.model.Sort
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.adopt.AdoptPostPage

internal fun AdoptPaginationResponse?.toDomain() = AdoptPostPage(
    adoptPostFeed = this?.adoptFeedEntitiyList?.map { it.toDomain() } ?: listOf(),
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

internal fun AdoptSortResponse?.toDomain() = Sort(
    empty = this?.empty ?: false,
    sorted = this?.sorted ?: false,
    unsorted = this?.unsorted ?: false
)

internal fun AdoptPageResponse?.toDomain() = Page(
    page = this?.page ?: 0,
    size = this?.size ?: 0
)

internal fun List<AdoptFeedRequestResponse>.toDomain(): List<AdoptPostFeed> {
    var temp: ArrayList<AdoptPostFeed> = ArrayList()
    this.forEach {
        temp.add(it.toDomain())
    }
    return temp
}

internal fun AdoptFeedRequestResponse.toDomain() = AdoptPostFeed(
    contents = contents ?: "",
    animalType = animalType ?: AnimalType.DOG,
    adoptionPostId = adoptionPostId ?: "",
    endDate = endDate ?: emptyList(),
    images = images,
    startDate = startDate ?: emptyList(),
    title = title ?: "",
    userId = userId ?: "",
    neutering = neutering ?: Neutering.NO,
    species = species ?: "",
    gender = gender ?: Gender.MALE,
    euthanasiaDate = euthanasiaDate ?: "",
    age = age,
    imageByteArrayList = listOf(),
    disease = disease ?: "없음"

)

internal fun AdoptPostFeed.toMapper() = AdoptFeedRequestResponse(
    contents = contents ?: "",
    animalType = animalType ?: AnimalType.DOG,
    adoptionPostId = adoptionPostId ?: "",
    endDate = endDate ?: emptyList(),
    images = images,
    startDate = startDate ?: emptyList(),
    title = title ?: "",
    userId = userId ?: "",
    neutering = neutering ?: Neutering.NO,
    species = species ?: "",
    gender = gender ?: Gender.MALE,
    euthanasiaDate = euthanasiaDate ?: "",
    age = age,
    disease = disease ?: "없음"

)
