package com.dev6.data.mapper

import com.dev6.data.model.daily.*
import com.dev6.domain.model.Page
import com.dev6.domain.model.Sort
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.model.daily.DailyPostPage
import com.dev6.domain.model.daily.DailyPostRequest

internal fun DailyPaginationResponse?.toDomain() = DailyPostPage(
    dailyFeedEntitiy = this?.dailyFeedEntitiy?.map { it.toDomain() } ?: listOf(),
    empty = this?.empty ?: false,
    first = this?.first ?: false,
    last = this?.last ?: false,
    number = this?.number ?: 0,
    numberOfElements = this?.numberOfElements ?: 0,
    pageable = this?.dailyPageEntitiy.toDomain(),
    size = this?.size ?: 0,
    dailySortEntitiy = this?.dailySortEntitiy.toDomain(),
    totalElements = this?.totalElements ?: 0,
    totalPages = this?.totalPages ?: 0
)

internal fun DailySortResponse?.toDomain() = Sort(
    empty = this?.empty ?: false,
    sorted = this?.sorted ?: false,
    unsorted = this?.unsorted ?: false
)

internal fun DailyPageResponse?.toDomain() = Page(
    page = this?.page ?: 0,
    size = this?.size ?: 0
)

internal fun List<DailyFeedRequestResponse>.toDomain(): List<DailyPost> {
    var temp: ArrayList<DailyPost> = ArrayList()
    this.forEach {
        temp.add(it.toDomain())
    }
    return temp
}

internal fun DailyFeedRequestResponse.toDomain() = DailyPost(
    contents = contents,
    images = images,
    title = title ?: "",
    userId = userId ?: "",
    normalPostId = normalPostId,
    likedCount = likedCount,
    createdDate = createdDate ?: listOf(),
    liked = liked ?: false,
    commentCount = commentCount ?: 0,
    imageList = listOf()

)

internal fun DailyPostRequest.toMapper() = DailyPostRequestResponse(
    contents = contents,
    images = images ?: emptyList(),
    title = title,
    userId = userId
)

internal fun DailyPost.toMapper() = DailyFeedRequestResponse(
    contents = contents,
    images = images,
    title = title,
    userId = userId,
    normalPostId = normalPostId,
    likedCount = likedCount,
    createdDate = createdDate,
    liked = liked,
    commentCount = commentCount
)
