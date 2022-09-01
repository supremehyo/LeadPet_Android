package com.dev6.data.mapper
import com.dev6.data.model.profile.ShelterUserDetailResponse
import com.dev6.data.model.profile.ShelterUserUpdateResponse
import com.dev6.domain.model.ProfileUserRepo
import com.dev6.domain.model.ProfileUserUpdateRepo

internal fun ShelterUserDetailResponse?.toDomain() = ProfileUserRepo(
    assessmentStatus = this?.assessmentStatus ?: "",
    profileImage = this?.profileImage ?: "",
    shelterAccount = this?.shelterAccount ?: "",
    shelterAddress = this?.shelterAddress ?: "",
    shelterHomepage = this?.shelterHomepage ?: "",
    shelterIntro = this?.shelterIntro ?: "",
    shelterManager = this?.shelterManager ?: "",
    shelterName = this?.shelterName ?: "",
    shelterPhoneNumber = this?.shelterPhoneNumber ?: "",
    userId = this?.userId ?: "",
)

internal fun ProfileUserUpdateRepo?.toDomain() = ShelterUserUpdateResponse(
    shelterAccount = this?.shelterAccount ?: "",
    shelterAddress = this?.shelterAddress ?: "",
    shelterIntro = this?.shelterIntro ?: "",
    shelterHomePage = this?.shelterHomePage ?: "",
    shelterManager = this?.shelterManager ?: "",
    shelterName = this?.shelterName ?: "",
    shelterPhoneNumber = this?.shelterPhoneNumber ?: "",
)
