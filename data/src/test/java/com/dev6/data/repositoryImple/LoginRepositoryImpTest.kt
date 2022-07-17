package com.dev6.data.repositoryImple

import com.dev6.core.enum.LoginType
import com.dev6.data.fake.FakeLoginDataSource
import com.dev6.domain.entitiyRepo.LoginEntity
import com.dev6.domain.entitiyRepo.UserEntity
import com.dev6.domain.repository.LoginRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class LoginRepositoryImpTest {

    private lateinit var remoteSource: FakeLoginDataSource
    private lateinit var repositoryImp: LoginRepository

    @Before
    fun setUp() {
        //이방식도 있고, Mock 방식도 있당
        remoteSource = FakeLoginDataSource()
        repositoryImp = LoginRepositoryImp(remoteSource)
    }

    @Test
    fun `로그인 주어짐_아무거나 들어오면_로그인 성공`() = runBlocking {
        //given
        val loginEntity = LoginEntity(loginMethod = LoginType.KAKAO, "테스트", "테스트", "테스트")

        //when
        val response = repositoryImp.login(loginEntity)

        //then
        Truth.assertThat(response.isSuccessful).isTrue()

    }

}