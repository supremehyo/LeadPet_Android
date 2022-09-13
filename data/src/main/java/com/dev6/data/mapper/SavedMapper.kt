package com.dev6.data.mapper

import com.dev6.data.model.saved.SavedResponse
import com.dev6.domain.model.save.Save

internal fun SavedResponse.toDomain() = Save(
    savedPostId = this.savedPostId
)
