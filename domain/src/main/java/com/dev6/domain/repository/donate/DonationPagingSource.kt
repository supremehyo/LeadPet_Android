package com.dev6.domain.repository.donate

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.donate.DonationPost

abstract class DonationPagingSource() : PagingSource<Int, DonationPost>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DonationPost>
    abstract override fun getRefreshKey(state: PagingState<Int, DonationPost>): Int?
}
