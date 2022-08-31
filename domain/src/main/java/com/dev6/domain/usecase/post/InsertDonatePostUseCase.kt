package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.repository.donate.DonaitonRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias InsertDonatePostBaseUseCase = BaseUseCase<DonationPost, Flow<UiState<DonationPost>>>

class InsertDonatePostUseCase(private val repo: DonaitonRepository) : InsertDonatePostBaseUseCase {

    override suspend fun invoke(donationPost: DonationPost): Flow<UiState<DonationPost>> = flow {
        emit(UiState.Loding)
        runCatching {
            repo.insertDonatePost(donationPost)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
