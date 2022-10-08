package com.dev6.data.mapper

import com.dev6.data.model.saved.*
import com.dev6.domain.model.Page
import com.dev6.domain.model.Pageable
import com.dev6.domain.model.save.*

internal fun SavedResponse.toDomain() = Save(
    savedPostId = this.savedPostId
)

internal fun SavedRequest.toDomain() = SavedPost(
    postId = this.postId,
    postType = this.postType,
    userId = this.userId
)

internal fun SavedPost.toData() = SavedRequest(
    postId = this.postId,
    postType = this.postType,
    userId = this.userId
)

internal fun DeleteSavedPost.toData() = DeleteSavedRequest(
    savedPostId = this.savedPostId,
    userId = this.userId
)


internal fun SavedPagingRequest.toDomain() =  Page(
    page = page,
    size = size
)

internal fun SavedAdoptionRequestResponse.toDomain() = SavedAdoptionData(
    images = images,
    adoptionPostId = adoptionPostId,
    title = title,
    userId = userId
)

internal fun SavedAdoptonPagingResponse.toDomain() = SavedAdoption(
    savedAdoptionRequestResponse = this?.savedAdoptionRequestResponse?.map {it.toDomain()}?: listOf(),
    empty = this?.empty ?: false,
    first = this?.first ?: false,
    last = this?.last ?: false,
    number = this?.number ?: 0,
    numberOfElements = this?.numberOfElements ?: 0,
    size = this?.size ?: 0,
    totalElements = this?.totalElements ?: 0,
    totalPages = this?.totalPages ?: 0,
    pageable = this.pageable.toDomain(),
    sort = this.sort.toDomain()
)
///

internal fun SavedDonationRequestResponse.toDomain() = SavedDonationData(
    images = images,
    donationPostId = donationPostId,
    title = title,
    userId = userId
)

internal fun SavedDonationPagingResponse.toDomain() = SavedDonation(
    savedDonationRequestResponse = this?.savedDonationRequestResponse?.map {it.toDomain()}?: listOf(),
    empty = this?.empty ?: false,
    first = this?.first ?: false,
    last = this?.last ?: false,
    number = this?.number ?: 0,
    numberOfElements = this?.numberOfElements ?: 0,
    size = this?.size ?: 0,
    totalElements = this?.totalElements ?: 0,
    totalPages = this?.totalPages ?: 0,
    pageable = this.pageable.toDomain(),
    sort = this.sort.toDomain()
)
///
internal fun SavedDailyRequestResponse.toDomain() = SavedDailyData(
    images = images,
    normalPostId = normalPostId,
    title = title,
    userId = userId
)

internal fun SavedDailyPagingResponse.toDomain() = SavedDaily(
    savedDailyRequestResponse = this?.savedDailyRequestResponse?.map {it.toDomain()}?: listOf(),
    empty = this?.empty ?: false,
    first = this?.first ?: false,
    last = this?.last ?: false,
    number = this?.number ?: 0,
    numberOfElements = this?.numberOfElements ?: 0,
    size = this?.size ?: 0,
    totalElements = this?.totalElements ?: 0,
    totalPages = this?.totalPages ?: 0,
    pageable = this.pageable.toDomain(),
    sort = this.sort.toDomain()
)
