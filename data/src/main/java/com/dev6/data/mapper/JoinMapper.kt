package com.dev6.data.mapper

import com.dev6.data.model.JoinEntitiy
import com.dev6.data.model.LifePostRequestResponse
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.entitiyRepo.LifePost

// 같은 모듈 안에서만 볼 수 있다
internal fun JoinEntitiy?.toDomain() = JoinEntitiyRepo(
    loginMethod = this?.loginMethod ?: "",
    uid = this?.uid ?: "",
    email = this?.email ?: "",
    password = this?.password ?: "",
    profileImage = this?.profileImage ?: "",
    name = this?.name ?: "",
    shelterName = this?.shelterName ?: "",
    shelterAddress = this?.shelterAddress ?: "",
    shelterPhoneNumber = this?.shelterPhoneNumber ?: "",
    shelterManager = this?.shelterManager ?: "",
    shelterHomePage = this?.shelterHomePage ?: "",
    userType = this?.userType ?: ""
)

internal fun JoinEntitiyRepo.toMapper() = JoinEntitiy(
    loginMethod = loginMethod,
    uid = uid,
    email = email,
    password = password,
    profileImage = profileImage,
    name = name,
    shelterName = shelterName,
    shelterAddress = shelterAddress,
    shelterPhoneNumber = shelterPhoneNumber,
    shelterManager = shelterManager,
    shelterHomePage = shelterHomePage,
    userType = userType
)