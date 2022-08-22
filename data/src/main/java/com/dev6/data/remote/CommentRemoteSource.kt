package com.dev6.data.remote

import com.dev6.data.model.comment.CommentPaginationResponse
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.service.FeedAPI
import timber.log.Timber
import javax.inject.Inject

interface CommentRemoteSource {
    suspend fun getCommentByPostId(postId: String, page: Int, size: Int): CommentPaginationResponse
}

class CommentRemoteSourceImpl @Inject constructor(
    private val feedService: FeedAPI
) : CommentRemoteSource {

    override suspend fun getCommentByPostId(
        postId: String,
        page: Int,
        size: Int
    ): CommentPaginationResponse {
        return feedService.getDailyCommentList(0, postId, size).executeNetworkHandling()
    }
}