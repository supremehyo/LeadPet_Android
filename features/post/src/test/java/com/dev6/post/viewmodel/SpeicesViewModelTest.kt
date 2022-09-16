package com.dev6.post.viewmodel

//@RunWith(MockitoJUnitRunner::class)
//class SpeicesViewModelTest{
//
//    @get:Rule
//    val coroutineRule = MainCoroutinesRule()
//
//    @Test
//    fun `게시글 정상적으로 들어왔을때_Event가 들어오는가?`() = runTest {
//
//        //given
//        val fakeGetSpeciesListUseCase = FakeGetSpeciesListUseCase()
//
//        //when
//        val adoptPostViewModel = AdoptPostViewModel(getSpeciesListUseCase = fakeGetSpeciesListUseCase)
//        //then
//
//        adoptPostViewModel.speciesStateFlow.test {
//            Truth.assertThat(awaitItem()).isInstanceOf(UiState.Success::class.java)
//            //캔슬시키고 꺼버림
//            cancelAndIgnoreRemainingEvents()
//        }
//    }
//
//
//}