package com.dev6.data.di

import com.dev6.data.repositoryImple.JoinRepositoryImple
import com.dev6.data.repositoryImple.LoginRepositoryImple
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


    @ViewModelScoped
    @Binds
    abstract fun bindsJoinRepository(repository: JoinRepositoryImple): JoinRepository

    @ViewModelScoped
    @Binds
    abstract fun bindsLoginRepository(repository: LoginRepositoryImple): LoginRepository


}

