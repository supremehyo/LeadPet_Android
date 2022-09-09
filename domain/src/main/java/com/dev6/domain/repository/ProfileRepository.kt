package com.dev6.domain.repository

import com.dev6.domain.model.NormalUserRepo
import com.dev6.domain.model.NormalUserUpdateRepo
import com.dev6.domain.model.ProfileUserRepo
import com.dev6.domain.model.ProfileUserUpdateRepo
import okhttp3.ResponseBody

interface ProfileRepository {
    suspend fun getProfileUserDetailData(userId: String): ProfileUserRepo
    suspend fun updateShelterProfileData(dto: ProfileUserUpdateRepo, userId: String): ResponseBody
    suspend fun  getNormalUserDetail (userId : String) : NormalUserRepo
    suspend fun  updateNormalUserData (dto : NormalUserUpdateRepo, userId : String) : ResponseBody
}
