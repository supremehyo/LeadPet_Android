package com.dev6.data.di

import com.dev6.data.repositoryImple.*
import com.dev6.domain.repository.*
import com.dev6.domain.repository.adopt.AdoptPagingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {


    @Binds
    @ViewModelScoped
    abstract fun bindsJoinRepository(repository: JoinRepositoryImple): JoinRepository


    @Binds
    @ViewModelScoped
    abstract fun bindsLoginRepository(repository: LoginRepositoryImp): LoginRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsAccessTokenRepository(repository: AccessTokenRepositoryImp): AccessTokenRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsDailyRepository(repositoryRepositoryImp: DailyRepositoryImp): DailyPagingRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsDonationRepository(repository: DonationPagingRepositoryImp): DonaitonPagingRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsAdoptRepository(repository: AdoptPagingRepositoryImp): AdoptPagingRepository
}

