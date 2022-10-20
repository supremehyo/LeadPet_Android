package com.dev6.data.network

import com.dev6.data.MainCoroutinesRule
import com.dev6.data.model.daily.DailyPostRequestResponse
import com.dev6.data.service.FeedAPI
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class FeedApiTest : ApiAbstract<FeedAPI>() {

    private lateinit var service: FeedAPI

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setRetrofit() {
        service = createService(FeedAPI::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun `게시글 삽입 테스트_성공`() {
        runBlocking {
            enqueueResponse("/lifepostinsert200.json")
            val response = service.insertDailyPost(
                dailyPostRequestResponse = DailyPostRequestResponse(
                    contents = "",
                    images = listOf(),
                    title = "",
                    userId = ""
                )
            )
            Truth.assertThat(response.body()?.userId).isEqualTo("테스트1app")
        }
    }

    @Throws(IOException::class)
    @Test
    fun `게시글 삽입 테스트_404`() {
        runBlocking {
            enqueueResponse("/lifepostinsert404.json", status = 404)
            val response = service.insertDailyPost(
                dailyPostRequestResponse = DailyPostRequestResponse(
                    contents = "",
                    images = listOf(),
                    title = "",
                    userId = ""
                )

            )
            mockWebServer.takeRequest()

            Truth.assertThat(response.body()).isNull()
            Truth.assertThat(response.code()).isEqualTo(404)
        }
    }
}
