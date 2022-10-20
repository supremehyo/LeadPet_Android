package com.dev6.domain.repository.saved

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.donate.DonationPost
import com.dev6.domain.model.save.SavedDonationData

abstract class SavedDonationPagingSource() : PagingSource<Int, SavedDonationData>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SavedDonationData>
    abstract override fun getRefreshKey(state: PagingState<Int, SavedDonationData>): Int?
}
