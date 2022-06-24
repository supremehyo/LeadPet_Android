package com.dev6.data.entity

import com.dev6.domain.entitiyRepo.LifePostEntityRepo

data class LifePostEntity(
    override val userId: String,
    override val title: String,
    override val contents: String,
    override val images: List<String>
) : LifePostEntityRepo {

}