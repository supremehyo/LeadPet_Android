package com.dev6.data.di

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        // 디버그 할때만 나오는 로그
        // Log.d("")
        Timber.d(request.toString())
        return chain.proceed(request)
    }
}