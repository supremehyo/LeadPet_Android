package com.dev6.data.mapper

import com.dev6.data.model.comment.CommentEntitiy
import com.dev6.data.model.comment.CommentPageEntitiy
import com.dev6.data.model.comment.CommentPaginationResponse
import com.dev6.data.model.comment.CommentSortEntitiy
import com.dev6.data.model.daily.DailyFeedEntitiy
import com.dev6.data.model.daily.DailyPageEntitiy
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.daily.DailySortEntitiy
import com.dev6.domain.entitiyRepo.*
import com.dev6.domain.entitiyRepo.comment.Comment
import com.dev6.domain.entitiyRepo.comment.CommentResponse
import com.dev6.domain.entitiyRepo.daily.DailyPost
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed

internal fun CommentPaginationResponse?.toDomain() = CommentResponse(
    commentEntitiy = this?.commentEntitiy?.map { it.toData() } ?: listOf(),
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

internal fun CommentSortEntitiy?.toDomain() = Sort(
    empty = this?.empty ?: false,
    sorted = this?.sorted ?: false,
    unsorted = this?.unsorted ?: false
)
internal fun CommentPageEntitiy?.toDomain() = Page(
    page = this?.page ?: 0,
    size = this?.size ?: 0
)

/*
internal fun List<CommentEntitiy>.toDomain(): List<Comment> {
    var temp: ArrayList<Comment> = ArrayList()
    this.forEach {
        temp.add(it.toData())
    }
    return temp
}
 */

internal fun CommentEntitiy.toData() = Comment(
    content = content,
    userId = userId,
    createdDate = createdDate,
    userName = userName,
    normalReplyId = normalReplyId
)
