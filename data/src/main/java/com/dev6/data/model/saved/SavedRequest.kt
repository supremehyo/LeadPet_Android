package com.dev6.data.model.saved

import com.dev6.core.enums.PostType

data class SavedRequest(
    val postId: String,
    val postType: PostType,
    val userId: String
)
