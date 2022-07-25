package com.dev6.domain.entitiyRepo

import java.io.Serializable

data class ShelterEntitiyRepo(
    val allFeedCount: Int,
    val assessmentStatus: String,
    val shelterName: String,
    val userId: String
) : Serializable
