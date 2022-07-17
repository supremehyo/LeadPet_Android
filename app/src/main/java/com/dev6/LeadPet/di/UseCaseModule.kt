package com.dev6.LeadPet.di

import com.dev6.data.repositoryImple.JoinRepositoryImple
import com.dev6.domain.repository.JoinRepository
import com.dev6.domain.repository.LifePostRepository
import com.dev6.domain.usecase.BaseUseCase
import com.dev6.domain.usecase.post.InsertLifePostBaseUseCase
import com.dev6.domain.usecase.post.InsertLifePostUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideInsertLifePostUsecase(repository: LifePostRepository): InsertLifePostBaseUseCase =
        InsertLifePostUseCase(repository)

}