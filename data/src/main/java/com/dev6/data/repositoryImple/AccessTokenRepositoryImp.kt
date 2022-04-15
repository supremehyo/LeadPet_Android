package com.dev6.data.repositoryImple

import android.app.Activity
import android.content.Context
import com.dev6.domain.repository.AccessTokenRepository
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class AccessTokenRepositoryImp @Inject constructor(private val context: Context) : AccessTokenRepository {

    override suspend fun getNaver(): String {
        TODO("Not yet implemented")
    }


    override suspend fun getKakao(): String {
        return suspendCancellableCoroutine<Result<String>> { cancellableContinuation ->
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                cancellableContinuation.resume(
                    when {
                        error != null -> Result.failure(error)
                        token != null -> Result.success(token.accessToken)
                        else -> Result.failure(Exception("Kakao API response is nothing."))
                    }
                )

            }

        }.getOrThrow()
    }

    override suspend fun getGoogle(): String {
        TODO("Not yet implemented")
    }
}