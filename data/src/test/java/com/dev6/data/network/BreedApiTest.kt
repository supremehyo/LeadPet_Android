package com.dev6.data.network

import com.dev6.data.MainCoroutinesRule
import com.dev6.data.model.executeNetworkHandling
import com.dev6.data.service.BreedAPI
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class BreedApiTest : ApiAbstract<BreedAPI>() {

    private lateinit var service: BreedAPI

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setRetrofit() {
        service = createService(BreedAPI::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun `게시글 삽입 테스트_성공`() {
        runBlocking {
            enqueueResponse("/getBreedList200.json")
            val response = service.fetchBreedList().executeNetworkHandling()
            Truth.assertThat(response.results[0].index).isEqualTo("가")
        }
    }
}
