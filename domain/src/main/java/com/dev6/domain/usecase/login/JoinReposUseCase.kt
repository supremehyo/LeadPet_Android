package com.dev6.domain.usecase.login

import android.util.Log
import com.dev6.core.base.UiState
import com.dev6.domain.image.FirebaseStorageRepository
import com.dev6.domain.model.Join
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias JoinReposBaseUseCase = BaseUseCase<Join, Flow<UiState<String>>>

class JoinReposUseCase @Inject constructor(
    private val joinRepository: JoinRepository,
    private val imageRepository: FirebaseStorageRepository
) :
    JoinReposBaseUseCase {

    override suspend fun invoke(join: Join) = flow {
        emit(UiState.Loding)
        runCatching {
            joinRepository.signUp(join.copy(
                profileImage = join.imageList.map{imageRepository.uploadImage(it)}[0])
            )

        }.onSuccess { result ->
            Log.v("ASdfasdf",join.profileImage)
            emit(UiState.Success(result))
        }.onFailure {

            emit(UiState.Error(it))
        }
    }
}
