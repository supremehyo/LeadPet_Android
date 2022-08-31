package com.dev6.data.mapper

import com.dev6.data.entity.SortXX
import com.dev6.data.model.shelter.ShelterResponse
import com.dev6.data.model.shelter.ShelterPageResponse
import com.dev6.data.model.shelter.ShelterPagingResponse
import com.dev6.domain.model.Page
import com.dev6.domain.model.ShelterEntitiyRepo
import com.dev6.domain.model.ShelterResopnseEntitiyRepo
import com.dev6.domain.model.Sort

internal fun ShelterPagingResponse?.toDomain() = ShelterResopnseEntitiyRepo(
    shelterEntitiy = this?.shelterEntitiy?.map { it.toDomain() } ?: listOf(),
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

internal fun SortXX?.toDomain() = Sort(
    empty = this?.empty ?: false,
    sorted = this?.sorted ?: false,
    unsorted = this?.unsorted ?: false
)

internal fun ShelterPageResponse?.toDomain() = Page(
    page = this?.page ?: 0,
    size = this?.size ?: 0
)


internal fun List<ShelterResponse>.toDomain(): List<ShelterEntitiyRepo> {
    var temp: ArrayList<ShelterEntitiyRepo> = ArrayList()
    this.forEach {
        temp.add(it.toDomain())
    }
    return temp
}

internal fun ShelterResponse.toDomain() = ShelterEntitiyRepo(
    allFeedCount = allFeedCount,
    assessmentStatus = assessmentStatus,
    profileImage = profileImage,
    shelterAddress = shelterAddress,
    shelterHomePage = shelterHomePage,
    shelterName = shelterName,
    userId = userId
)

