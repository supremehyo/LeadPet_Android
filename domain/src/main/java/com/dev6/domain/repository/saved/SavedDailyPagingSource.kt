package com.dev6.domain.repository.saved

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.save.SavedDailyData

abstract class SavedDailyPagingSource() : PagingSource<Int, SavedDailyData>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SavedDailyData>
    abstract override fun getRefreshKey(state: PagingState<Int, SavedDailyData>): Int?
}
