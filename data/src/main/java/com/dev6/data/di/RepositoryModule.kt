package com.dev6.data.di

import com.dev6.data.repositoryImple.AccessTokenRepositoryImp
import com.dev6.data.repositoryImple.JoinRepositoryImple
import com.dev6.data.repositoryImple.LoginRepositoryImp
import com.dev6.domain.repository.AccessTokenRepository
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.repository.LoginRepository
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
}

