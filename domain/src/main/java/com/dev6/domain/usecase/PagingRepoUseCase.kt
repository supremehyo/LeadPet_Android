package com.dev6.domain.usecase

import androidx.paging.PagingData
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.domain.repository.DailyPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PagingRepoUseCase @Inject constructor
    (private val dailyPagingRepository: DailyPagingRepository
) {
    /*
    fun getPagingData() : Flow<PagingData<DailyPostFeed>> {
     //   return dailyPagingRepository.getPagingData()
    }

     */
}