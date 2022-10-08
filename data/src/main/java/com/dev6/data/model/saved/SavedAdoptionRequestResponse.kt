package com.dev6.data.model.saved

import com.google.gson.annotations.SerializedName

data class SavedAdoptionRequestResponse(
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("adoptionPostId")
    val adoptionPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,
)
