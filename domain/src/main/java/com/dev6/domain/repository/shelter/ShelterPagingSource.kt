package com.dev6.domain.repository.shelter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.ShelterEntitiyRepo

abstract class ShelterPagingSource () : PagingSource<Int, ShelterEntitiyRepo>(){
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShelterEntitiyRepo>
    abstract override fun getRefreshKey(state: PagingState<Int, ShelterEntitiyRepo>): Int?
}
