package com.dev6.data.mapper

import com.dev6.data.model.daily.DailyFeedEntitiy
import com.dev6.data.model.daily.DailyPageEntitiy
import com.dev6.data.model.daily.DailyPaginationResponse
import com.dev6.data.model.daily.DailySortEntitiy
import com.dev6.domain.entitiyRepo.*
import com.dev6.domain.entitiyRepo.daily.DailyPost
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed

internal fun DailyPaginationResponse?.toDomain() = DailyPost(
    dailyFeedEntitiy = this?.dailyFeedEntitiy?.map { it.toData() } ?: listOf(),
    empty = this?.empty ?: false,
    first = this?.first ?: false,
    last = this?.last ?: false,
    number = this?.number ?: 0,
    numberOfElements = this?.numberOfElements ?: 0,
    pageable = this?.dailyPageEntitiy.toDomain(),
    size = this?.size ?: 0,
    dailySortEntitiy = this?.dailySortEntitiy .toDomain(),
    totalElements = this?.totalElements ?: 0,
    totalPages = this?.totalPages ?: 0
)

internal fun DailySortEntitiy?.toDomain() = Sort(
    empty = this?.empty ?: false,
    sorted = this?.sorted ?: false,
    unsorted = this?.unsorted ?: false
)
internal fun DailyPageEntitiy?.toDomain() = Page(
    page = this?.page ?: 0,
    size = this?.size ?: 0
)


internal fun List<DailyFeedEntitiy>.toDomain(): List<DailyPostFeed> {
    var temp: ArrayList<DailyPostFeed> = ArrayList()
    this.forEach {
        temp.add(it.toData())
    }
    return temp
}

internal fun DailyFeedEntitiy.toData() = DailyPostFeed(
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
