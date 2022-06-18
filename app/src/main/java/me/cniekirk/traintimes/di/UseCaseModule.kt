package me.cniekirk.traintimes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.cniekirk.traintimes.domain.usecase.*

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetDepartureBoardUseCase(getDepartureBoardUseCaseImpl: GetDepartureBoardUseCaseImpl): GetDepartureBoardUseCase

    @Binds
    abstract fun provideGetStationsUseCase(getStationsUseCaseImpl: GetStationsUseCaseImpl): GetStationsUseCase

    @Binds
    abstract fun provideGetServiceDetailsUseCase(getServiceDetailsUseCaseImpl: GetServiceDetailsUseCaseImpl): GetServiceDetailsUseCase
}