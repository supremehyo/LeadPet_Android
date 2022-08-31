package com.dev6.domain.repository

import androidx.paging.PagingData
import com.dev6.domain.model.comment.CommentPage
import kotlinx.coroutines.flow.Flow

interface CommentPagingRepository {
    suspend fun getCommentPagingData(postId: String): Flow<PagingData<CommentPage>>
}