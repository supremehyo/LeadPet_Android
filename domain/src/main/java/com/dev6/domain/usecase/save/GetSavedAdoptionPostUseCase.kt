package com.dev6.domain.usecase.save

import androidx.paging.PagingData
import com.dev6.core.base.UiState
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow

typealias GetSavedAdoptionPostBaseUseCase = BaseUseCase<DonationPost, Flow<UiState<PagingData<DonationPost>>>>

class GetSavedAdoptionPostUseCase : GetSavedAdoptionPostBaseUseCase {

    override suspend fun invoke(params: DonationPost): Flow<UiState<PagingData<DonationPost>>> {
        TODO("Not yet implemented")
    }
}
