package com.dev6.data.model.shelter

data class ShelterResponse(
    val allFeedCount: Int,
    val assessmentStatus: String,
    val profileImage: String,
    val shelterAddress: String,
    val shelterHomePage: String,
    val shelterName: String,
    val userId: String
)