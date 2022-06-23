package com.dev6.data.di

import com.dev6.data.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    const val BASE_URL = "http://02b2-123-212-235-38.ngrok.io"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .connectTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
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
    fun provideLifeService(retrofit: Retrofit): LifePostAPI {
        return retrofit.create(LifePostAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDonateService(retrofit: Retrofit): DonatePostAPI {
        return retrofit.create(DonatePostAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideAdoptService(retrofit: Retrofit): AdoptPostAPI {
        return retrofit.create(AdoptPostAPI::class.java)
    }
}