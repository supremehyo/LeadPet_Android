package com.dev6.LeadPet.di

import com.dev6.data.repositoryImp.*
import com.dev6.domain.image.FirebaseStorageRepository
import com.dev6.domain.repository.*
import com.dev6.domain.repository.adopt.AdoptRepository
import com.dev6.domain.repository.daily.DailyRepository
import com.dev6.domain.repository.donate.DonationRepository
import com.dev6.domain.repository.saved.SavedRepository
import com.dev6.domain.repository.shelter.ShelterPagingRepository
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
    abstract fun bindsJoinRepository(repository: JoinRepositoryImp): JoinRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsLoginRepository(repository: LoginRepositoryImp): LoginRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsAccessTokenRepository(repository: AccessTokenRepositoryImp): AccessTokenRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsDailyRepository(repositoryRepositoryImp: DailyRepositoryImp): DailyRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsDonationRepository(repository: DonationRepositoryImp): DonationRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsAdoptPagingRepository(repository: AdoptRepositoryImp): AdoptRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsShelterRepository(repository: ShelterRepositoryImp): ShelterPagingRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsBreedRepository(repository: BreedRepositoryImp): BreedRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsCommentRepository(repository: CommentRepositoryImp): CommentPagingRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsProfileRepository(repository: ProfileRepositoryImp): ProfileRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsSavedRepository(repository: SavedRepositoryImp): SavedRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsFirebaseStorageRepository(repository: FirebaseStorageRepositoryImp): FirebaseStorageRepository
}
