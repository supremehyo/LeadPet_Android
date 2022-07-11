package com.dev6.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

abstract class SamplePagingSource () : PagingSource<Int, Any >(){
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any>
    abstract override fun getRefreshKey(state: PagingState<Int, Any>): Int?
}


