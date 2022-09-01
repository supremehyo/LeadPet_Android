package com.dev6.domain.repository.shelter

import androidx.paging.PagingData
import com.dev6.domain.model.ShelterEntitiyRepo
import kotlinx.coroutines.flow.Flow

interface ShelterPagingRepository {
    suspend fun getPagingData(cityName:String,shelterName:String) : Flow<PagingData<ShelterEntitiyRepo>>
}