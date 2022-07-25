package com.dev6.data.model.shelter

import com.dev6.data.entity.SortXX
import com.dev6.data.model.PageEntitiy
import com.dev6.data.model.SortEntitiy
import com.dev6.domain.entitiyRepo.Sort
import com.google.gson.annotations.SerializedName

data class ShelterPagingResponse(
    @SerializedName("content")
    val shelterEntitiy: List<ShelterEntitiy>,
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
    val page: PageEntitiy,
    @SerializedName("size")
    val size: Int,
    @SerializedName("sort")
    val sort: SortXX,
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)