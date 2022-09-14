package com.dev6.LeadPet.di

import com.dev6.domain.repository.BreedRepository
import com.dev6.domain.repository.daily.DailyRepository
import com.dev6.domain.repository.donate.DonationRepository
import com.dev6.domain.repository.saved.SavedRepository
import com.dev6.domain.usecase.post.*
import com.dev6.domain.usecase.save.DeleteSavedPostBaseUseCase
import com.dev6.domain.usecase.save.DeleteSavedPostUseCase
import com.dev6.domain.usecase.save.InsertSavedPostBaseUseCase
import com.dev6.domain.usecase.save.InsertSavedPostUseCase
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

//    @Provides
//    @ViewModelScoped
//    fun provideInsertAdoptPostUseCase(repository: AdoptRepository): InsertAdoptPostBaseUseCase =
//        InsertAdoptPostUseCase(repository)
//
//    @Provides
//    @ViewModelScoped
//    fun provideInsertAdoptPostUseCase(repository: AdoptRepository): InsertAdoptPostBaseUseCase =
//        InsertAdoptPostUseCase(repository)

//    @Provides
//    @ViewModelScoped
//    fun provideGetSavedAdoptionPostUseCase(repository: SavedRepository): GetSavedAdoptionPostBaseUseCase =
//        GetSavedAdoptionPostUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideDeleteSavedPostUseCase(repository: SavedRepository): DeleteSavedPostBaseUseCase =
        DeleteSavedPostUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideInsertSavedPostUseCase(repository: SavedRepository): InsertSavedPostBaseUseCase =
        InsertSavedPostUseCase(repository)
}
