package com.dev6.domain.usecase

import android.util.Log
import com.dev6.core.base.UiState
import com.dev6.domain.image.FirebaseStorageRepository
import com.dev6.domain.model.NormalUserUpdateRepo
import com.dev6.domain.model.ProfileUserUpdateRepo
import com.dev6.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepoUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val imageRepository: FirebaseStorageRepository
) {
    fun getShelterProfileDetailData(userId: String) = flow {
        emit(UiState.Loding)
        runCatching {
            profileRepository.getProfileUserDetailData(userId)
        }.onSuccess { result ->

            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

    fun updateShelterProfileDetailData(dto: ProfileUserUpdateRepo, userId: String) = flow {
        emit(UiState.Loding)
        runCatching {
            profileRepository.updateShelterProfileData(dto, userId)

        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

    fun getNormalUserProfileDetailData(userId: String) = flow{
        emit(UiState.Loding)
        runCatching {
            profileRepository.getNormalUserDetail(userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

    fun updateNormalUserProfileDetailData(dto: NormalUserUpdateRepo, userId: String) = flow{
        emit(UiState.Loding)
        runCatching {
            val imageUpload = dto.imageList.map{imageRepository.uploadImage(it)}[0]
            val updateDTO = dto.copy(profileImage =  imageRepository.fetchImage(imageUpload))
            profileRepository.updateNormalUserData(updateDTO,userId)
            updateDTO.profileImage
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

    fun normalUserProfileImageUri(uuid: String) = flow{
        emit(UiState.Loding)
        runCatching {
            imageRepository.fetchImage(uuid)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
