package com.dev6.domain.usecase

import androidx.paging.PagingData
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.repository.PagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PagingRepoUseCase @Inject constructor (private val pagingRepository: PagingRepository) {

    fun getPagingData() : Flow<PagingData<Any>> {
        return pagingRepository.getPagingData()
    }
}