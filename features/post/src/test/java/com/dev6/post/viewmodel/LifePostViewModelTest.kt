package com.dev6.post.viewmodel

import app.cash.turbine.test
import com.dev6.core.base.UiState
import com.dev6.domain.entitiyRepo.LifePost
import com.dev6.domain.usecase.post.InsertLifePostUseCase
import com.dev6.post.MainCoroutinesRule
import com.dev6.post.fake.FakeInsertLifePostUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LifePostViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutinesRule()

    @Test
    fun `게시글 정상적으로 들어왔을때_Event가 들어오는가?`() = runBlocking {

        //given
        val insertLifePostUseCase = FakeInsertLifePostUseCase()

        //when
        val lifePostViewModel = LifePostViewModel(insertLifePostUseCase = insertLifePostUseCase)
        //then

        lifePostViewModel.insertLifePost("", "" )

        lifePostViewModel.eventFlow.test {
            Truth.assertThat(awaitItem()).isInstanceOf(LifePostViewModel.Event.UiEvent::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }
}