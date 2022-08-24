package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.daily.DailyPostFeed
import com.dev6.domain.repository.LifePostRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias InsertDailyPostBaseUseCase = BaseUseCase<DailyPostFeed, Flow<UiState<DailyPostFeed>>>

class InsertLifePostUseCase @Inject constructor(private val repo: LifePostRepository) : InsertDailyPostBaseUseCase {

    override suspend fun invoke(lifePost: DailyPostFeed) = flow {
        emit(UiState.Loding)
        runCatching {
            repo.insertLifePost(lifePost)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
