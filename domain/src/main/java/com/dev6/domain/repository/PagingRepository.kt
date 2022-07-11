package com.dev6.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.domain.entitiyRepo.DonationPostFeed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

abstract class PagingRepository () {
    abstract fun getPagingData(): Flow<PagingData<Any>>
}
