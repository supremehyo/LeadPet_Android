package com.dev6.data.remote

import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.model.profile.ShelterUserDetailEntitiy
import com.dev6.data.model.profile.ShelterUserUpdateEntitiy
import com.dev6.data.service.ProfileAPI
import okhttp3.ResponseBody
import javax.inject.Inject


interface ProfileRemoteSource {
    suspend fun  getShelterProfileDetailData (userId : String ) : ShelterUserDetailEntitiy
    suspend fun  updateShelterProfileData(dto : ShelterUserUpdateEntitiy, userId : String) : ResponseBody
}

class ProfileRemoteSourceImp @Inject constructor(
    private val profileService : ProfileAPI
) : ProfileRemoteSource {
    override suspend fun getShelterProfileDetailData(userId: String): ShelterUserDetailEntitiy {
        return profileService.getShelterUserDetail(userId).executeNetworkHandling()
    }

    override suspend fun updateShelterProfileData(dto: ShelterUserUpdateEntitiy , userId : String): ResponseBody {
        return profileService.updateShelterData(userId , dto).executeNetworkHandling()
    }
}