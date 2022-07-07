package com.dev6.domain.usecase.post

import app.cash.turbine.test
import com.dev6.core.base.UiState
import com.dev6.core.enum.LoginType
import com.dev6.core.exception.ServerFailException
import com.dev6.domain.entitiyRepo.LifePost
import com.dev6.domain.entitiyRepo.LoginEntity
import com.dev6.domain.repository.LifePostRepository
import com.dev6.domain.repository.LoginRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class InsertLifePostUseCaseTest {

    private lateinit var repositoryImp: LifePostRepository
    private lateinit var insertLifePostUseCase: InsertLifePostUseCase

    @Test
    fun `게시글 정상적으로 들어왔을때_Result Success로 반환되는가?`() = runBlocking {
        //given
        val loginEntity = LifePost(
            userId = "",
            title = "",
            contents = "",
            images = listOf(),
            normalPostId = null
        )

        repositoryImp = Mockito.mock(LifePostRepository::class.java)

        Mockito.`when`(repositoryImp.insertLifePost(loginEntity)).thenReturn(loginEntity)

        //when
        insertLifePostUseCase = InsertLifePostUseCase(repositoryImp)

        //then
        insertLifePostUseCase(loginEntity).test {
            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Loding::class.java)
            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Success::class.java)
            awaitComplete()
        }
    }

    @Test
    @Throws(ServerFailException::class)
    fun `게시글 정상적이지않을때_Result Error로 반환되는가?`() = runBlocking {
        //given
        val loginEntity = LifePost(
            userId = "",
            title = "",
            contents = "",
            images = listOf(),
            normalPostId = null
        )

        repositoryImp = Mockito.mock(LifePostRepository::class.java)

        Mockito.`when`(repositoryImp.insertLifePost(loginEntity))
            .thenAnswer { ServerFailException("테스뚜") }

        //when
        insertLifePostUseCase = InsertLifePostUseCase(repositoryImp)

        //then
        insertLifePostUseCase(loginEntity).test {
            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Loding::class.java)
            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Error::class.java)
            awaitComplete()
        }
    }

}

