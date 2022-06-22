package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo

interface DonateRepository {
    suspend fun insertDonatePost(postEntity: DonatePostEntityRepo): Boolean
}