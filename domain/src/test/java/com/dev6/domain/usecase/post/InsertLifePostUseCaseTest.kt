package com.dev6.domain.usecase.post

import app.cash.turbine.test
import com.dev6.core.base.UiState
import com.dev6.core.exception.ServerFailException
import com.dev6.domain.model.daily.DailyPost
import com.dev6.domain.repository.daily.DailyRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InsertLifePostUseCaseTest {

    private lateinit var repositoryImp: DailyRepository
    private lateinit var insertLifePostUseCase: InsertDailyPostUseCase

    @Test
    fun `게시글 정상적으로 들어왔을때_Result Success로 반환되는가?`() = runBlocking {
        // given
        val loginEntity = DailyPost(
            contents = "",
            images = listOf(),
            normalPostId = "",
            title = "",
            userId = "",
            likedCount = 0,
            createdDate = listOf(),
            commentCount = 0,
            liked = false

        )

        repositoryImp = Mockito.mock(DailyRepository::class.java)

        Mockito.`when`(repositoryImp.insertDailyPost(loginEntity)).thenReturn(loginEntity)

        // when
        insertLifePostUseCase = InsertDailyPostUseCase(repositoryImp)

        // then
        insertLifePostUseCase(loginEntity).test {
            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Loding::class.java)
            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Success::class.java)
        }
    }

    @Test
    @Throws(ServerFailException::class)
    fun `게시글 정상적이지않을때_Result Error로 반환되는가?`() = runBlocking {
        // given
        val loginEntity = DailyPost(
            contents = "",
            images = listOf(),
            normalPostId = "",
            title = "",
            userId = "",
            likedCount = 0,
            createdDate = listOf(),
            commentCount = 0,
            liked = false

        )

        repositoryImp = Mockito.mock(DailyRepository::class.java)

        Mockito.`when`(repositoryImp.insertDailyPost(loginEntity))
            .thenAnswer { ServerFailException("테스뚜") }

        // when
        insertLifePostUseCase = InsertDailyPostUseCase(repositoryImp)

        // then
        insertLifePostUseCase(loginEntity).test {
            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Loding::class.java)
            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Error::class.java)
            awaitComplete()
        }
    }
}
