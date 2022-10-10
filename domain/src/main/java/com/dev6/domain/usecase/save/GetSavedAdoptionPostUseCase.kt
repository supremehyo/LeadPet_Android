package com.dev6.domain.usecase.save

import androidx.paging.PagingData
import com.dev6.core.base.UiState
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.SavedAdoptionData
import com.dev6.domain.repository.saved.SavedRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetSavedAdoptionPostUseCase @Inject constructor (private val repo: SavedRepository) {

    fun getSavedAdoptPagingData(userId: String) = flow {
        emit(UiState.Loding)
        runCatching {
            repo.getSavedAdoptionPagingData(userId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}

