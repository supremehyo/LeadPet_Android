/*
package com.dev6.domain.usecase.save

import androidx.paging.PagingData
import com.dev6.core.base.UiState
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.repository.saved.SavedRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias GetSavedAdoptionPostBaseUseCase = BaseUseCase<DonationPost, Flow<UiState<PagingData<DonationPost>>>>

class GetSavedAdoptionPostUseCase(private val repo: SavedRepository) : GetSavedAdoptionPostBaseUseCase {

    override suspend fun invoke(params: DonationPost): Flow<UiState<PagingData<DonationPost>>> = flow {
        emit(UiState.Loding)
        runCatching {
            repo.getSavedAdoptionPagingData(params.userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
*/
