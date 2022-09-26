package com.dev6.domain.usecase.post

import com.dev6.core.base.UiState
import com.dev6.domain.image.FirebaseStorageRepository
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.repository.daily.DailyRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias InsertDailyPostBaseUseCase = BaseUseCase<DailyPost, Flow<UiState<DailyPost>>>

class InsertDailyPostUseCase @Inject constructor(
    private val repo: DailyRepository,
    private val imageRepository: FirebaseStorageRepository
) : InsertDailyPostBaseUseCase {

    override suspend fun invoke(dailyPost: DailyPost) = flow {
        emit(UiState.Loding)
        runCatching {
            repo.insertDailyPost(
                dailyPost.copy(images = dailyPost.imageList.map { imageRepository.uploadImage(it) })
            )
        }.onSuccess { result ->
            emit(UiState.Success(result))
        }.onFailure {
            emit(UiState.Error(it))
        }
    }
}
