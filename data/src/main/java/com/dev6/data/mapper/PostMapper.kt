package com.dev6.data.mapper

import com.dev6.data.model.LifePostRequestResponse
import com.dev6.domain.entitiyRepo.LifePost

// 같은 모듈 안에서만 볼 수 있다
internal fun LifePostRequestResponse?.toDomain() = LifePost(
    userId = this?.userId ?: "",
    title = this?.title ?: "",
    contents = this?.contents ?: "",
    images = this?.images ?: mutableListOf(),
    normalPostId = this?.normalPostId ?: ""
)

internal fun LifePost.toMapper() = LifePostRequestResponse(
    userId = userId,
    title = title,
    contents = contents,
    images = images,
    normalPostId = normalPostId
)