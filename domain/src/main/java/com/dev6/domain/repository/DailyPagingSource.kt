package com.dev6.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import javax.inject.Inject

abstract class DailyPagingSource () : PagingSource<Int, DailyPostFeed>(){
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DailyPostFeed>
    abstract override fun getRefreshKey(state: PagingState<Int, DailyPostFeed>): Int?
}


