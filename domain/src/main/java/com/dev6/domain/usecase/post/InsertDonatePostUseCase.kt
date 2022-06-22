package com.dev6.domain.usecase.post

import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import com.dev6.domain.repository.DonateRepository

class InsertDonatePostUseCase(private val repo: DonateRepository) {
    suspend operator fun invoke(donatePostEntityRepo: DonatePostEntityRepo) = runCatching {
        repo.insertDonatePost(donatePostEntityRepo)
    }
}