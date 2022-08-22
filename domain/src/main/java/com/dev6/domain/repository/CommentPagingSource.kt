package com.dev6.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev6.domain.entitiyRepo.comment.Comment
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import javax.inject.Inject

abstract class CommentPagingSource () : PagingSource<Int, Comment>(){
    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment>
    abstract override fun getRefreshKey(state: PagingState<Int, Comment>): Int?
}


