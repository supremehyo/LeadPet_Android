package com.dev6.domain.repository.saved

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.donate.DonationPost

abstract class SavedDonationPagingSource() : PagingSource<Int, DonationPost>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DonationPost>
    abstract override fun getRefreshKey(state: PagingState<Int, DonationPost>): Int?
}
