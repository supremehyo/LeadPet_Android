package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.model.adopt.AdoptPostFeed
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

typealias InsertAdoptPostBaseUseCase = BaseUseCase<AdoptPostFeed, Flow<UiState<AdoptPostFeed>>>

class InsertAdoptPostUseCase(private val repo: AdoptPostRepository) : InsertAdoptPostBaseUseCase {

    suspend operator fun invoke(adoptPostEntityRepo: AdoptPostEntityRepo) = flow<UiState<AdoptPostFeed>> {
        emit(UiState.Loding)
        runCatching {
            repo.insertAdoptPost(adoptPostEntityRepo)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
