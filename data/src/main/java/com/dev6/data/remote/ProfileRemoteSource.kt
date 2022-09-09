package com.dev6.data.remote

import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.model.profile.ShelterUserDetailResponse
import com.dev6.data.model.profile.ShelterUserUpdateResponse
import com.dev6.data.model.profile.UserDetailResponse
import com.dev6.data.model.profile.UserUpdateRequest
import com.dev6.data.service.ProfileAPI
import okhttp3.ResponseBody
import javax.inject.Inject


interface ProfileRemoteSource {
    suspend fun  getShelterProfileDetailData (userId : String ) : ShelterUserDetailResponse
    suspend fun  updateShelterProfileData(dto : ShelterUserUpdateResponse, userId : String) : ResponseBody
    suspend fun  getNormalUserDetail (userId : String) : UserDetailResponse
    suspend fun  updateNormalUserData (dto : UserUpdateRequest, userId : String) : ResponseBody

}

class ProfileRemoteSourceImp @Inject constructor(
    private val profileService : ProfileAPI
) : ProfileRemoteSource {
    override suspend fun getShelterProfileDetailData(userId: String): ShelterUserDetailResponse {
        return profileService.getShelterUserDetail(userId).executeNetworkHandling()
    }

    override suspend fun updateShelterProfileData(dto: ShelterUserUpdateResponse, userId : String): ResponseBody {
        return profileService.updateShelterData(userId , dto).executeNetworkHandling()
    }

    override suspend fun getNormalUserDetail(userId: String): UserDetailResponse {
        return profileService.getNormalUserDetail(userId).executeNetworkHandling()
    }

    override suspend fun updateNormalUserData(
        dto: UserUpdateRequest,
        userId: String
    ): ResponseBody {
        return profileService.updateNormalUserData(userId , dto).executeNetworkHandling()
    }
}