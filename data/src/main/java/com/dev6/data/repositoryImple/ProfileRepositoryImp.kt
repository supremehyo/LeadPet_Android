package com.dev6.data.repositoryImple

import com.dev6.data.mapper.toData
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.ProfileRemoteSource
import com.dev6.domain.entitiyRepo.ProfileUserRepo
import com.dev6.domain.entitiyRepo.ProfileUserUpdateRepo
import com.dev6.domain.repository.ProfileRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(
    private val profileRemoteSource: ProfileRemoteSource
) : ProfileRepository {
    override suspend fun getProfileUserDetailData(userId: String): ProfileUserRepo {
        return profileRemoteSource.getShelterProfileDetailData(userId).toDomain()
    }

    override suspend fun updateShelterProfileData(
        dto: ProfileUserUpdateRepo,
        userId: String
    ): ResponseBody {
        return profileRemoteSource.updateShelterProfileData(dto.toData() ,userId)
    }
}