package me.cniekirk.traintimes.features.search.ui

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.cniekirk.traintimes.di.UseCaseModule
import me.cniekirk.traintimes.domain.usecase.GetDepartureBoardUseCase
import me.cniekirk.traintimes.domain.usecase.GetServiceDetailsUseCase
import me.cniekirk.traintimes.domain.usecase.GetStationsUseCase

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UseCaseModule::class]
)
abstract class FakeUseCaseModule {

    @Binds
    abstract fun provideGetDepartureBoardUseCase(fakeGetDepartureBoardUseCase: FakeGetDepartureBoardUseCase): GetDepartureBoardUseCase

    @Binds
    abstract fun provideGetStationsUseCase(fakeGetStationsUseCase: FakeGetStationsUseCase): GetStationsUseCase

    @Binds
    abstract fun provideGetServiceDetailsUseCase(fakeGetServiceDetailsUseCase: FakeGetServiceDetailsUseCase): GetServiceDetailsUseCase
}