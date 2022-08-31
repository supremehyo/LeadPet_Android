package com.dev6.LeadPet.di

import com.dev6.domain.repository.BreedRepository
import com.dev6.domain.usecase.post.GetSpeciesListBaseUseCase
import com.dev6.domain.usecase.post.GetSpeciesListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

}