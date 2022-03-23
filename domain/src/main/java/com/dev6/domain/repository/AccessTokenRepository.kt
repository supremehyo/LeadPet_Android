package com.dev6.domain.repository


//AccessToken의 인터페이스
interface AccessTokenRepository {
    suspend fun  getNaver() : String
    suspend fun  getKakao() : String
    suspend fun  getGoogle() : String

}