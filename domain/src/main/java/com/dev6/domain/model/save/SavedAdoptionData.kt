package com.dev6.domain.model.save

import com.google.gson.annotations.SerializedName

data class SavedAdoptionData(
    @SerializedName("images")
    val images: List<String>?,
    @SerializedName("adoptionPostId")
    val adoptionPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,
)
