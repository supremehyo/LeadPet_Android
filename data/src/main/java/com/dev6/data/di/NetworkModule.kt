package com.dev6.data.di

import com.dev6.data.service.FeedAPI
import com.dev6.data.service.JoinAPI
import com.dev6.data.service.LoginAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    const val BASE_URL = "http://192.168.0.17:8080"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .connectTimeout(5 , TimeUnit.SECONDS )
            .callTimeout(5 ,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideJoinService(retrofit: Retrofit): JoinAPI {
        return retrofit.create(JoinAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginAPI {
        return retrofit.create(LoginAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideFeedService(retrofit: Retrofit): FeedAPI {
        return retrofit.create(FeedAPI::class.java)
    }
}