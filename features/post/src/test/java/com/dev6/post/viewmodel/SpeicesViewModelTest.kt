package com.dev6.post.viewmodel

import app.cash.turbine.test
import com.dev6.core.base.UiState
import com.dev6.post.MainCoroutinesRule
import com.dev6.post.fake.FakeGetSpeciesListUseCase
import com.dev6.post.fake.FakeInsertLifePostUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SpeicesViewModelTest{

    @get:Rule
    val coroutineRule = MainCoroutinesRule()

    @Test
    fun `게시글 정상적으로 들어왔을때_Event가 들어오는가?`() = runTest {

        //given
        val fakeGetSpeciesListUseCase = FakeGetSpeciesListUseCase()

        //when
        val SpeicesViewModel = SpeicesViewModel(getSpeciesListUseCase = fakeGetSpeciesListUseCase)
        //then

        SpeicesViewModel.speciesStateFlow.test {
            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Success::class.java)
            //캔슬시키고 꺼버림
            cancelAndIgnoreRemainingEvents()
        }
    }


}