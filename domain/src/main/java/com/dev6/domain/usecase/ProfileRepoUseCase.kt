package com.dev6.domain.usecase

import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.ProfileUserUpdateRepo
import com.dev6.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepoUseCase @Inject constructor(
    private val profileRepository : ProfileRepository
) {
    fun getShelterProfileDetailData(userId : String) = flow{
        emit(UiState.Loding)
        runCatching {
            profileRepository.getProfileUserDetailData(userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

    fun updateShelterProfileDetailData(dto : ProfileUserUpdateRepo , userId : String) = flow{
        emit(UiState.Loding)
        runCatching {
            profileRepository.updateShelterProfileData(dto ,userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}