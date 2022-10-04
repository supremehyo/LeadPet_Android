package com.dev6.domain.usecase

import android.util.Log
import com.dev6.core.base.UiState
import com.dev6.domain.repository.shelter.ShelterPagingRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ShelterPagingRepoUseCase @Inject constructor(
    private val shelterPagingRepository: ShelterPagingRepository
) {

   suspend fun getShelterPagingData(cityName:String,shelterName:String) = shelterPagingRepository.getPagingData(cityName, shelterName)


}

