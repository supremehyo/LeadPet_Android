package com.dev6.domain.repository.adopt

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.adopt.AdoptPostFeed

abstract class AdoptPagingSource () : PagingSource<Int, AdoptPostFeed>(){
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AdoptPostFeed>
    abstract override fun getRefreshKey(state: PagingState<Int, AdoptPostFeed>): Int?
}
