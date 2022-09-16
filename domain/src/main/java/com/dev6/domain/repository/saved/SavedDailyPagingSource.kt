package com.dev6.domain.repository.saved

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.daily.DailyPost

abstract class SavedDailyPagingSource() : PagingSource<Int, DailyPost>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DailyPost>
    abstract override fun getRefreshKey(state: PagingState<Int, DailyPost>): Int?
}
