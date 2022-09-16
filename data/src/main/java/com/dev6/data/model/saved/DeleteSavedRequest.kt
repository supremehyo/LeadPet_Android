package com.dev6.data.model.saved

import com.dev6.core.enums.PostType

data class DeleteSavedRequest(
    val savedPostId: String,
    val userId: String
)
