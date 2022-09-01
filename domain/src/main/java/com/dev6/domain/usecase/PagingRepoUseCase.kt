package com.dev6.domain.usecase

import com.dev6.domain.repository.daily.DailyRepository
import javax.inject.Inject

class PagingRepoUseCase @Inject constructor(
    private val dailyPagingRepository: DailyRepository
) {
    /*
    fun getPagingData() : Flow<PagingData<DailyPostFeed>> {
     //   return dailyPagingRepository.getPagingData()
    }

     */
}
