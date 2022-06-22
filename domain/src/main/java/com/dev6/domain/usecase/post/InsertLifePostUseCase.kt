package com.dev6.domain.usecase.post

import com.dev6.domain.entitiyRepo.DonatePostEntityRepo
import com.dev6.domain.entitiyRepo.LifePosEntityRepo
import com.dev6.domain.repository.LifeRepository

class InsertLifePostUseCase(private val repo: LifeRepository) {
    suspend operator fun invoke(lifePosEntityRepo: LifePosEntityRepo) = runCatching {
        repo.insertLifePost(lifePosEntityRepo)
    }
}