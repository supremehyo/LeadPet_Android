package com.dev6.domain.usecase

import com.dev6.domain.repository.AccessTokenRepository
import javax.inject.Inject

class GetKakaoAccessTokenUseCase@Inject constructor(private val accessToken : AccessTokenRepository){

    suspend operator  fun invoke() = runCatching { accessToken.getKakao()}
}