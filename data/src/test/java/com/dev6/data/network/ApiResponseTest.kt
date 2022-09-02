/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dev6.data.network

import com.dev6.core.exception.ServerFailException
import com.dev6.data.model.ErrorResponse
import com.google.common.truth.Truth
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Custom Response 테스트
 *
 */
@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun `에러 404가 났을때_네트워크 핸들링하면_오류가 처리되는가?`() {
        Assert.assertThrows(ServerFailException::class.java) {
            runBlocking {
//                val apiResponse =
//                    Response(ErrorResponse(code = 404, message = "", detail = ""), data = String)
//                val response = apiResponse.executeNetworkHandling()
            }
        }
    }

    @Test
    fun `code가 200일때_네트워크 핸들랑을 하면_오류가 처리되는가?`() {
        runBlocking {
//            val apiResponse = Response(data = String)
//            val response = apiResponse.executeNetworkHandling()
        }
    }

    @Test
    fun `에러났을때_네트워크 핸들랑을 하면_파싱이 되는가??`() {
        runBlocking {
            val gson = Gson()
            val jsonObject =
                JsonParser.parseString("{\"error\":{\"code\":404,\"message\":\"NOT_FOUND\",\"detail\":\"Error: 존재하지 않는 유저\"}}\n")
            val errorResponse = gson.fromJson(jsonObject, ErrorResponse::class.java)
            Truth.assertThat(errorResponse).isNotNull()
        }
    }
}
