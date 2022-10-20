package com.dev6.data.repositoryImp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev6.data.mapper.toData
import com.dev6.data.remote.CommentRemoteSource

import com.dev6.domain.model.comment.Comment
import com.dev6.domain.model.comment.CommentUpdate
import com.dev6.domain.repository.CommentPagingRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject

class CommentRepositoryImp
@Inject constructor(
    private val commentRemoteSource: CommentRemoteSource
) : CommentPagingRepository {
    override suspend fun getCommentPagingData(postId: String): Flow<PagingData<Comment>> {
        return Pager(PagingConfig(pageSize = 10)) { CommentPagingSourceImp(postId, commentRemoteSource) }.flow
    }

    override suspend fun postCommentData(
        commentUpdate : CommentUpdate
    ): ResponseBody = commentRemoteSource.postCommentData(commentUpdate.toData())
}
