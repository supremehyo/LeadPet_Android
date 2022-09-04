package com.dev6.domain.repository

import androidx.paging.PagingData
import com.dev6.domain.model.comment.Comment
import com.dev6.domain.model.comment.CommentPage
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface CommentPagingRepository {
    suspend fun getCommentPagingData(postId: String): Flow<PagingData<Comment>>
    suspend fun postCommentData(content : String , normalPostId : String,userId: String) : ResponseBody
}