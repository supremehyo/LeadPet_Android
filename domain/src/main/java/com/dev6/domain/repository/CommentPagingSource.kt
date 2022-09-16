package com.dev6.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.model.comment.Comment

abstract class CommentPagingSource() : PagingSource<Int, Comment>() {
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment>
    abstract override fun getRefreshKey(state: PagingState<Int, Comment>): Int?
}
