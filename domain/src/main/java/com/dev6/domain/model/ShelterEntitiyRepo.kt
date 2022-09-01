package com.dev6.domain.model

import java.io.Serializable

data class ShelterEntitiyRepo(
    val allFeedCount: Int,
    val assessmentStatus: String,
    val profileImage: String,
    val shelterAddress: String,
    val shelterHomePage: String,
    val shelterName: String,
    val userId: String
) : Serializable
