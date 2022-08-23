package com.dev6.data.mapper
import com.dev6.data.model.profile.ShelterUserDetailEntitiy
import com.dev6.data.model.profile.ShelterUserUpdateEntitiy
import com.dev6.domain.entitiyRepo.ProfileUserRepo
import com.dev6.domain.entitiyRepo.ProfileUserUpdateRepo

internal fun ShelterUserDetailEntitiy?.toDomain() = ProfileUserRepo(
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

internal fun ProfileUserUpdateRepo?.toData() = ShelterUserUpdateEntitiy(
    shelterAccount = this?.shelterAccount ?: "",
    shelterAddress = this?.shelterAddress ?: "",
    shelterIntro = this?.shelterIntro ?: "",
    shelterHomePage = this?.shelterHomePage ?: "",
    shelterManager = this?.shelterManager ?: "",
    shelterName = this?.shelterName ?: "",
    shelterPhoneNumber = this?.shelterPhoneNumber ?: "",
)
