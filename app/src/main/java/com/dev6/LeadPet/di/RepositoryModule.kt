package com.dev6.LeadPet.di

import com.dev6.data.repositoryImple.JoinRepositoryImple
import com.dev6.domain.repository.JoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsJoinRepository(repository: JoinRepositoryImple): JoinRepository
}

