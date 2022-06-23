package com.dev6.domain.usecase.post

import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import com.dev6.domain.repository.DonatePostRepository

class InsertDonatePostUseCase(private val repo: DonatePostRepository) {
    suspend operator fun invoke(donatePostEntityRepo: DonatePostEntityRepo) = runCatching {
        repo.insertDonatePost(donatePostEntityRepo)
    }
}