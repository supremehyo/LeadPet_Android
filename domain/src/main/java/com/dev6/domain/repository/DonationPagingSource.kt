package com.dev6.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.entitiyRepo.DonationPostFeed

abstract class DonationPagingSource () : PagingSource<Int, DonationPostFeed>(){
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DonationPostFeed>
    abstract override fun getRefreshKey(state: PagingState<Int, DonationPostFeed>): Int?
}
