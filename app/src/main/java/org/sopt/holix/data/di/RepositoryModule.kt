package org.sopt.holix.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.holix.data.repository.ClubRepositoryImpl
import org.sopt.holix.data.repository.DummyRepositoryImpl
import org.sopt.holix.data.repository.MainRepositoryImpl
import org.sopt.holix.domain.repository.ClubRepository
import org.sopt.holix.domain.repository.DummyRepository
import org.sopt.holix.domain.repository.MainRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository

    @Binds
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    fun bindClubRepository(clubRepositoryImpl: ClubRepositoryImpl): ClubRepository

}
