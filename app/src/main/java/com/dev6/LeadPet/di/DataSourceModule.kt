package com.dev6.LeadPet.di

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


    @Singleton
    @Binds
    abstract fun bindsSamplePagingRemoteSource(source: DailyRemoteSourceImpl): DailyRemoteSource

    @Singleton
    @Binds
    abstract fun bindsDonationPagingRemoteSource(source: DonationRemoteSourceImpl): DonationRemoteSource

    @Singleton
    @Binds
    abstract fun bindsAdoptPagingRemoteSource(source: AdoptRemoteSourceImpl): AdoptRemoteSource

    @Singleton
    @Binds
    abstract fun bindsShelterRemoteSource(source: ShelterRemoteSourceImpl): ShelterRemoteSource

    @Singleton
    @Binds
    abstract fun bindsBreedRemoteSource(source: BreedRemoteSourceImpl): BreedRemoteSource

    @Singleton
    @Binds
    abstract fun bindsCommentRemoteSource(source: CommentRemoteSourceImpl): CommentRemoteSource

    @Singleton
    @Binds
    abstract fun bindsProfileRemoteSource(source: ProfileRemoteSourceImp): ProfileRemoteSource
}

