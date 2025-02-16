package com.dev6.data.model.saved
import com.dev6.data.model.SortResponse
import com.google.gson.annotations.SerializedName

data class SavedDailyPagingResponse(
    @SerializedName("content")
    val savedDailyRequestResponse: List<SavedDailyRequestResponse>,
    @SerializedName("empty")
    val empty: Boolean,
    @SerializedName("first")
    val first: Boolean,
    @SerializedName("last")
    val last: Boolean,
    @SerializedName("number")
    val number: Int,
    @SerializedName("numberOfElements")
    val numberOfElements: Int,
    @SerializedName("pageable")
    val pageable: SavedPagingRequest,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val sort: SortResponse,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)
