package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo

interface AdoptRepository {
    suspend fun insertAdoptPost(postEntity: AdoptPostEntityRepo): Boolean
}