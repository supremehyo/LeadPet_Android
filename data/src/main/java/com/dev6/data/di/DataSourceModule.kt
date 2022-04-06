package com.dev6.data.di

import com.dev6.data.remote.JoinRemoteSource
import com.dev6.data.remote.JoinRemoteSourceImpl
import com.dev6.data.remote.LoginRemoteSource
import com.dev6.data.remote.LoginRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindsJoinRemoteSource(source: JoinRemoteSourceImpl): JoinRemoteSource

    @Singleton
    @Binds
    abstract fun bindsLoginRemoteSource(source: LoginRemoteSourceImpl): LoginRemoteSource
}

