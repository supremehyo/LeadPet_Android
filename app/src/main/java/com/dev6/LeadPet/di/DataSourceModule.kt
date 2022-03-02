package com.dev6.LeadPet.di

import com.dev6.data.remote.JoinRemoteSource
import com.dev6.data.remote.JoinRemoteSourceImpl
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
}

