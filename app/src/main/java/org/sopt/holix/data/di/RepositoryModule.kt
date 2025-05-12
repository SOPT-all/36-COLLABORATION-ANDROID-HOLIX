package org.sopt.holix.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.holix.data.repository.DummyRepositoryImpl
import org.sopt.holix.domain.repository.DummyRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository
}