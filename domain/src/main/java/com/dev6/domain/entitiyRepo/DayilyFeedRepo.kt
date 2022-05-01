package com.dev6.domain.entitiyRepo

interface DayilyFeedRepo {
    val _contents : String
    val _images : List<String>
    val _normalPostId : String
    val _tags : List<String>
    val _title : String
    val _userId : String
}