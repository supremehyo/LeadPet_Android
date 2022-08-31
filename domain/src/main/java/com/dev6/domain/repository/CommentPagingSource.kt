package com.dev6.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.comment.CommentPage

abstract class CommentPagingSource() : PagingSource<Int, CommentPage>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommentPage>
    abstract override fun getRefreshKey(state: PagingState<Int, CommentPage>): Int?
}
