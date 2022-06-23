package com.dev6.domain.usecase.post

import com.dev6.domain.entitiyRepo.LifePosEntityRepo
import com.dev6.domain.repository.LifePostRepository

class InsertLifePostUseCase(private val repo: LifePostRepository) {
    suspend operator fun invoke(lifePosEntityRepo: LifePosEntityRepo) = runCatching {
        repo.insertLifePost(lifePosEntityRepo)
    }
}