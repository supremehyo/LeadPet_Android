package com.dev6.post.viewmodel

//@RunWith(MockitoJUnitRunner::class)
//class LifePostViewModelTest {
//
//    @get:Rule
//    val coroutineRule = MainCoroutinesRule()
//
//    @Test
//    fun `게시글 정상적으로 들어왔을때_Event가 들어오는가?`() = runBlocking {
//
//        //given
//        val insertLifePostUseCase = FakeInsertLifePostUseCase()
//
//        //when
//        val lifePostViewModel = LifePostViewModel(insertLifePostUseCase = insertLifePostUseCase)
//        //then
//
//        lifePostViewModel.insertLifePost("", "" )
//
//        lifePostViewModel.eventFlow.test {
//            Truth.assertThat(awaitItem()).isInstanceOf(LifePostViewModel.Event.UiEvent::class.java)
//            cancelAndIgnoreRemainingEvents()
//        }
//    }
//}