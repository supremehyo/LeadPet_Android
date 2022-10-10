package com.dev6.data.mapper

import com.dev6.core.enum.AnimalType
import com.dev6.core.enum.Gender
import com.dev6.core.enum.Neutering
import com.dev6.core.enums.DonationMethod
import com.dev6.data.model.saved.*
import com.dev6.domain.model.Page
import com.dev6.domain.model.Pageable
import com.dev6.domain.model.adopt.AdoptPostFeed
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
    contents = contents ?: "",
    animalType = animalType ?: AnimalType.DOG,
    adoptionPostId = adoptionPostId ?: "",
    endDate = endDate ?: emptyList(),
    images = images,
    startDate = startDate ?: emptyList(),
    title = title ?: "",
    userId = userId ?: "",
    neutering = neutering?: Neutering.NO,
    species = species?: "",
    gender = gender?: Gender.MALE,
    euthanasiaDate = euthanasiaDate?: "",
    disease = disease?: "없음",
    age = age,
    userName = userName,
    profileImage = profileImage
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
    contents = this?.contents ?: "",
    donationMethod = this?.donationMethod ?: DonationMethod.GOODS,
    donationPostId = this?.donationPostId ?: "",
    endDate = this?.endDate ?: emptyList(),
    images = this?.images ?: emptyList(),
    startDate = this?.startDate ?: emptyList(),
    title = this?.title ?: "",
    userId = this?.userId ?: "",
    userName = userName,
    profileImage = profileImage
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
    contents = contents,
    images = images,
    title = title,
    userId = userId,
    normalPostId = normalPostId,
    createdDate = createdDate,
    userName = userName,
    profileImage = profileImage ?: ""
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


internal fun SavedAdoptionData.toFeed() = AdoptPostFeed(
    adoptionPostId = adoptionPostId,
    age = age,
    animalType = animalType,
    contents = contents?: "",
    endDate =endDate,
    euthanasiaDate =euthanasiaDate ?: "",
    disease = disease,
    gender= gender,
    images = images,
    neutering =neutering,
    species = species ?: "",
    userName = userName,
    profileImage = profileImage,
    userId = userId ?: "",
    startDate = startDate ?: emptyList(),
    title =  title ?: ""
)
