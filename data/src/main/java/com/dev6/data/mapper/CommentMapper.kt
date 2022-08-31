package com.dev6.data.mapper

import com.dev6.data.model.comment.CommentPageResposne
import com.dev6.data.model.comment.CommentPaginationResponse
import com.dev6.data.model.comment.CommentResponse
import com.dev6.data.model.comment.CommentSortResponse
import com.dev6.domain.model.Page
import com.dev6.domain.model.Sort
import com.dev6.domain.model.comment.Comment
import com.dev6.domain.model.comment.CommentPage

internal fun CommentPaginationResponse?.toDomain() = CommentPage(
    commentEntitiy = this?.commentEntitiy?.map { it.toDomain() } ?: listOf(),
    empty = this?.empty ?: false,
    first = this?.first ?: false,
    last = this?.last ?: false,
    number = this?.number ?: 0,
    numberOfElements = this?.numberOfElements ?: 0,
    commentPageEntitiy = this?.commentPageEntitiy.toDomain(),
    size = this?.size ?: 0,
    commentSortEntitiy = this?.commentSortEntitiy.toDomain(),
    totalElements = this?.totalElements ?: 0,
    totalPages = this?.totalPages ?: 0
)

internal fun CommentSortResponse?.toDomain() = Sort(
    empty = this?.empty ?: false,
    sorted = this?.sorted ?: false,
    unsorted = this?.unsorted ?: false
)

internal fun CommentPageResposne?.toDomain() = Page(
    page = this?.page ?: 0,
    size = this?.size ?: 0
)

internal fun CommentResponse.toDomain() = Comment(
    content = content,
    userId = userId,
    createdDate = createdDate,
    userName = userName,
    normalReplyId = normalReplyId
)
