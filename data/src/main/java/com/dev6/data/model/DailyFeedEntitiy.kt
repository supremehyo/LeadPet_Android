package com.dev6.data.entity

import com.dev6.domain.entitiyRepo.DayilyFeedRepo

data class DailyFeedEntitiy(
    val contents : String,
    val images : List<String>,
    val normalPostId : String,
    val tags : List<String>,
    val title : String,
    val userId : String
) : DayilyFeedRepo {
    override val _contents: String
        get() = contents
    override val _images: List<String>
        get() = images
    override val _normalPostId: String
        get() = normalPostId
    override val _tags: List<String>
        get() = tags
    override val _title: String
        get() = title
    override val _userId: String
        get() = userId
}