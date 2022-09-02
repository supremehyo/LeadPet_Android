/*
package com.dev6.data.network

import com.dev6.data.MainCoroutinesRule
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class LoginApiTest : ApiAbstract<LifePostAPI>() {

    private lateinit var service: LifePostAPI

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setRetrofit() {
        service = createService(LifePostAPI::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun `게시글 삽입 테스트_성공`() {
        runBlocking {
            enqueueResponse("/lifepostinsert200.json")
            val response = service.addNewLifePost(
                lifePostRequest = LifePostRequestResponse(
                    userId = "",
                    title = "",
                    contents = "",
                    images = listOf(),
                    normalPostId = null
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
            val response = service.addNewLifePost(
                lifePostRequest = LifePostRequestResponse(
                    userId = "",
                    title = "",
                    contents = "",
                    images = listOf(),
                    normalPostId = null
                )
            )
            mockWebServer.takeRequest()

            Truth.assertThat(response.body()).isNull()
            Truth.assertThat(response.code()).isEqualTo(404)
        }
    }
}
*/
