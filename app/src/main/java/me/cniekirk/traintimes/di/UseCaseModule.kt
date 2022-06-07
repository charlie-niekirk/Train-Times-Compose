package me.cniekirk.traintimes.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.cniekirk.traintimes.domain.usecase.GetDepartureBoardUseCase
import me.cniekirk.traintimes.domain.usecase.GetDepartureBoardUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetDepartureBoardUseCase(getDepartureBoardUseCaseImpl: GetDepartureBoardUseCaseImpl): GetDepartureBoardUseCase
        = getDepartureBoardUseCaseImpl
}