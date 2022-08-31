package com.dev6.data.mapper

import com.dev6.data.model.JoinResponse
import com.dev6.domain.model.Join

// 같은 모듈 안에서만 볼 수 있다
internal fun JoinResponse?.toDomain() = Join(
    loginMethod = this?.loginMethod ?: "",
    uid = this?.uid ?: "",
    email = this?.email ?: "",
    password = this?.password ?: "",
    profileImage = this?.profileImage ?: "",
    name = this?.name ?: "",
    shelterName = this?.shelterName ?: "",
    shelterAccount = this?.shelterAccount ?: "",
    shelterAddress = this?.shelterAddress ?: "",
    shelterPhoneNumber = this?.shelterPhoneNumber ?: "",
    shelterManager = this?.shelterManager ?: "",
    shelterHomePage = this?.shelterHomePage ?: "",
    userType = this?.userType ?: "",
    shelterIntro = this?.shelterIntro ?: ""
)

internal fun Join.toMapper() = JoinResponse(
    loginMethod = loginMethod,
    uid = uid,
    email = email,
    password = password,
    profileImage = profileImage,
    name = name,
    shelterName = shelterName,
    shelterAccount = shelterAccount,
    shelterAddress = shelterAddress,
    shelterPhoneNumber = shelterPhoneNumber,
    shelterManager = shelterManager,
    shelterHomePage = shelterHomePage,
    userType = userType,
    shelterIntro = shelterIntro
)