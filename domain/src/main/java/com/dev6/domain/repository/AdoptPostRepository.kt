package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo

interface AdoptPostRepository {
    suspend fun insertAdoptPost(postEntity: AdoptPostEntityRepo): Boolean
}