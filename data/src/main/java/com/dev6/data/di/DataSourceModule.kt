package com.dev6.data.di

import com.dev6.data.remote.*
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

    @Singleton
    @Binds
    abstract fun bindsLifePostRemoteSource(source: LifePostRemoteImpl): LifePostRemoteSource

    @Singleton
    @Binds
    abstract fun bindsDonatePostRemoteSource(source: DonatePostRemoteSourceImpl): DonatePostRemoteSource

    @Singleton
    @Binds
    abstract fun bindsAdoptPostRemoteSource(source: AdoptPostRemoteSource): AdoptPostRemoteSource

}

