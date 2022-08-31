package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.repository.daily.DailyRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias InsertDailyPostBaseUseCase = BaseUseCase<DailyPost, Flow<UiState<DailyPost>>>

class InsertLifePostUseCase @Inject constructor(private val repo: DailyRepository) : InsertDailyPostBaseUseCase {

    override suspend fun invoke(lifePost: DailyPost) = flow {
        emit(UiState.Loding)
        runCatching {
            repo.insertDailyPost(lifePost)
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
