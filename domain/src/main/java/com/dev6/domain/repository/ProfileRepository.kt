package com.dev6.domain.repository

import com.dev6.domain.model.ProfileUserRepo
import com.dev6.domain.model.ProfileUserUpdateRepo
import okhttp3.ResponseBody

interface ProfileRepository {
    suspend fun getProfileUserDetailData(userId: String): ProfileUserRepo
    suspend fun updateShelterProfileData(dto: ProfileUserUpdateRepo, userId: String): ResponseBody
}
