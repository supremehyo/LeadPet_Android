package com.dev6.data.model.saved

import com.dev6.core.enum.AnimalType
import com.dev6.core.enum.Gender
import com.dev6.core.enum.Neutering
import com.google.gson.annotations.SerializedName

data class SavedAdoptionRequestResponse(
    @SerializedName("adoptionPostId")
    val adoptionPostId: String?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("animalType")
    val animalType: AnimalType,
    @SerializedName("contents")
    val contents: String?,
    @SerializedName("endDate")
    val endDate: List<String>?,
    @SerializedName("disease")
    val disease : String,
    @SerializedName("euthanasiaDate")
    val euthanasiaDate: String?,
    @SerializedName("gender")
    val gender: Gender,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("neutering")
    val neutering: Neutering,
    @SerializedName("breed")
    val species: String?,
    @SerializedName("startDate")
    val startDate: List<String>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("userId")
    val userId: String?,
    val imageByteArrayList: List<ByteArray>
    /*
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("adoptionPostId")
    val adoptionPostId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,

     */
)
