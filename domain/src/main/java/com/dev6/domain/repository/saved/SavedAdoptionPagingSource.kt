package com.dev6.domain.repository.saved

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.model.save.SavedAdoptionData

abstract class SavedAdoptionPagingSource() : PagingSource<Int, SavedAdoptionData>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SavedAdoptionData>
    abstract override fun getRefreshKey(state: PagingState<Int, SavedAdoptionData>): Int?
}
