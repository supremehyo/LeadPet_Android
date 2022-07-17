package com.dev6.domain.repository

import com.dev6.domain.entitiyRepo.DonatePostEntityRepo

interface DonatePostRepository {
    suspend fun insertDonatePost(postEntity: DonatePostEntityRepo): Boolean
}
