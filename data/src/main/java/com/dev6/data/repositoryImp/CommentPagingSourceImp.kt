package com.dev6.data.repositoryImp

import android.util.Log
import androidx.paging.PagingState
import com.dev6.data.mapper.toDomain
import com.dev6.data.remote.CommentRemoteSource
import com.dev6.domain.model.comment.Comment
import com.dev6.domain.repository.CommentPagingSource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CommentPagingSourceImp @Inject constructor(
    postId: String,
    private val commentRemoteSource: CommentRemoteSource
) : CommentPagingSource() {
    var _postId = postId
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val next = params.key ?: 0
            val size = params.loadSize
            val response = commentRemoteSource.getCommentByPostId(_postId, next, size)
            try {
                Log.v("ssssfsfsf2", response.toDomain().commentEntitiy.get(0).normalReplyId)
            } catch (e: IOException) {
                return LoadResult.Error(e)
            } catch (e: HttpException) {
                return LoadResult.Error(e)
            }
            if(response.last){
                LoadResult.Page(
                    data = response.toDomain().commentEntitiy,
                    prevKey = null,
                    nextKey = null
                )
            }else{
                LoadResult.Page(
                    data = response.toDomain().commentEntitiy,
                    prevKey = null,
                    nextKey = next+1
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
