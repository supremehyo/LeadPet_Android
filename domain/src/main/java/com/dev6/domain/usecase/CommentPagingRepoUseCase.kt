package com.dev6.domain.usecase

import android.util.Log
import com.dev6.core.base.UiState
import com.dev6.domain.model.comment.CommentUpdate
import com.dev6.domain.repository.CommentPagingRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CommentPagingRepoUseCase @Inject constructor
    (
    private val commentPagingRepository: CommentPagingRepository
) {

    fun getCommentDataPagingData(postId: String) = flow {
        emit(UiState.Loding)
        runCatching {
            commentPagingRepository.getCommentPagingData(postId)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }

    fun postCommentData(commentUpdate : CommentUpdate) = flow {
        emit(UiState.Loding)
        runCatching {
            commentPagingRepository.postCommentData(commentUpdate)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}

