package com.dev6.data.mapper

import com.dev6.data.model.saved.DeleteSavedRequest
import com.dev6.data.model.saved.SavedRequest
import com.dev6.data.model.saved.SavedResponse
import com.dev6.domain.model.save.DeleteSavedPost
import com.dev6.domain.model.save.Save
import com.dev6.domain.model.save.SavedPost

internal fun SavedResponse.toDomain() = Save(
    savedPostId = this.savedPostId
)

internal fun SavedRequest.toDomain() = SavedPost(
    postId = this.postId,
    postType = this.postType,
    userId = this.userId
)

internal fun SavedPost.toData() = SavedRequest(
    postId = this.postId,
    postType = this.postType,
    userId = this.userId
)

internal fun DeleteSavedPost.toData() = DeleteSavedRequest(
    savedPostId = this.savedPostId,
    userId = this.userId
)
