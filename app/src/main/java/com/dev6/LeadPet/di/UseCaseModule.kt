package com.dev6.LeadPet.di

import com.dev6.domain.repository.BreedRepository
import com.dev6.domain.repository.adopt.AdoptRepository
import com.dev6.domain.repository.daily.DailyRepository
import com.dev6.domain.repository.donate.DonationRepository
import com.dev6.domain.usecase.post.*
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
    fun provideDailyPostUsecase(repository: DailyRepository): InsertDailyPostBaseUseCase =
        InsertDailyPostUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideInsertDonatePostUseCase(repository: DonationRepository): InsertDonatePostBaseUseCase =
        InsertDonatePostUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetSpeciesListUsecase(repository: BreedRepository): GetSpeciesListBaseUseCase =
        GetSpeciesListUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideInsertAdoptPostUseCase(repository: AdoptRepository): InsertAdoptPostBaseUseCase =
        InsertAdoptPostUseCase(repository)
}