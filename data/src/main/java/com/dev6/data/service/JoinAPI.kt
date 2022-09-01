package com.dev6.data.service

import com.dev6.data.model.JoinResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface  JoinAPI {

    @Headers("Content-Type: application/json")
    @POST("v1/user/signup")
    suspend fun signUp(@Body userEntitiy: JoinResponse): String  //Response<String> 로 변경해서 응답코드에 맞는 로직 처리로 수정 필요

}