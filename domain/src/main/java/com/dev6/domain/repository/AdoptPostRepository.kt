package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed

interface AdoptPostRepository {
    suspend fun insertAdoptPost(postEntity: AdoptPostFeed): AdoptPostFeed
}