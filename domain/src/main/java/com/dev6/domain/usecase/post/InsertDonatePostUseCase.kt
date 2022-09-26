package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.image.FirebaseStorageRepository
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.repository.donate.DonationRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias InsertDonatePostBaseUseCase = BaseUseCase<DonationPost, Flow<UiState<DonationPost>>>

class InsertDonatePostUseCase @Inject constructor(private val donationRepository: DonationRepository, private val imageRepository: FirebaseStorageRepository) :
    InsertDonatePostBaseUseCase {
    override suspend fun invoke(donationPost: DonationPost): Flow<UiState<DonationPost>> = flow {
        emit(UiState.Loding)
        runCatching {
//            imageRepository.uploadImage()
            donationRepository.insertDonatePost(donationPost)

        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
