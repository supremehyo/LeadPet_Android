package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.ProfileUserRepo
import com.dev6.domain.entitiyRepo.ProfileUserUpdateRepo
import okhttp3.ResponseBody

interface ProfileRepository {
    suspend fun getProfileUserDetailData(userId :String) : ProfileUserRepo
    suspend fun  updateShelterProfileData(dto : ProfileUserUpdateRepo, userId : String) : ResponseBody
}