package com.dev6.domain.entitiyRepo.adopt

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AdoptPostFeed(
    @SerializedName("adoptionPostId")
    val adoptionPostId: String,
    @SerializedName("animalType")
    val animalType: String,
    @SerializedName("contents")
    val contents: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("euthanasiaDate")
    val euthanasiaDate: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("neutering")
    val neutering: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String
) : Serializable