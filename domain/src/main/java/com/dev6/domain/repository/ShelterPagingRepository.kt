package com.dev6.domain.repository

import androidx.paging.PagingData
import com.dev6.domain.entitiyRepo.ShelterEntitiyRepo
import kotlinx.coroutines.flow.Flow

interface ShelterPagingRepository {
    suspend fun getPagingData(cityName:String,shelterName:String) : Flow<PagingData<ShelterEntitiyRepo>>
}