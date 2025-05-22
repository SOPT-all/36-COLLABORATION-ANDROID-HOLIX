package org.sopt.holix.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.holix.data.service.ChattingService
import org.sopt.holix.data.service.ClubService
import org.sopt.holix.data.service.DummyService
import org.sopt.holix.data.service.HomeService
import org.sopt.holix.data.service.MainService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideDummyService(retrofit: Retrofit): DummyService =
        retrofit.create(DummyService::class.java)

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Provides
    @Singleton
    fun provideChattingService(retrofit: Retrofit): ChattingService =
        retrofit.create(ChattingService::class.java)

    @Provides
    @Singleton
    fun provideClubService(retrofit: Retrofit): ClubService =
        retrofit.create(ClubService::class.java)

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)
}
