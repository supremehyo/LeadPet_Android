package com.dev6.data.model.adopt

import com.google.gson.annotations.SerializedName

data class AdoptFeedEntitiy(
    @SerializedName("adoptionPostId")
    val adoptionPostId: String?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("animalType")
    val animalType: String?,
    @SerializedName("contents")
    val contents: String?,
    @SerializedName("endDate")
    val endDate: String?,
    @SerializedName("euthanasiaDate")
    val euthanasiaDate: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("neutering")
    val neutering: String?,
    @SerializedName("breed")
    val species: String?,
    @SerializedName("startDate")
    val startDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: String?
)