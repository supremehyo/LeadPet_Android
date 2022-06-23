package com.dev6.data.di

import com.dev6.data.repositoryImple.*
import com.dev6.domain.repository.*
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
    abstract fun bindsLifeRepository(repository: LifePostRepositoryImp): LifePostRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsDonateRepository(repository: DonatePostRepositoryImp): DonatePostRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsAdoptRepository(repository: AdoptPostRepositoryImp): AdoptPostRepository
}

