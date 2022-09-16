package com.dev6.domain.model.save

import com.dev6.core.enums.PostType

data class SavedPost(
    val postId: String,
    val postType: PostType,
    val userId: String
)