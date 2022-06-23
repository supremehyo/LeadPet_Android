package com.dev6.domain.usecase.post

import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo
import com.dev6.domain.repository.AdoptPostRepository

class InsertAdoptPostUseCase(private val repo: AdoptPostRepository) {

    suspend operator fun invoke(adoptPostEntityRepo: AdoptPostEntityRepo) = runCatching {
        repo.insertAdoptPost(adoptPostEntityRepo)
    }
}