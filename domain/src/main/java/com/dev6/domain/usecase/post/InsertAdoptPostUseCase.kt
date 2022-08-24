package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.AdoptPostEntityRepo
import com.dev6.domain.entitiyRepo.adopt.AdoptPostFeed
import com.dev6.domain.repository.AdoptPostRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

typealias InsertAdoptPostBaseUseCase = BaseUseCase<AdoptPostFeed, Flow<UiState<AdoptPostFeed>>>

class InsertAdoptPostUseCase(private val repo: AdoptPostRepository) : InsertAdoptPostBaseUseCase {

    suspend operator fun invoke(adoptPostEntityRepo: AdoptPostEntityRepo) = flow<UiState<Boolean>> {
        emit(UiState.Loding)
        runCatching {
            repo.insertAdoptPost(adoptPostEntityRepo)
        }.onSuccess { result ->
            if (result) emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
