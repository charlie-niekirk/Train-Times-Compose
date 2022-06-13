package me.cniekirk.traintimes.features.search.ui

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.cniekirk.traintimes.di.UseCaseModule
import me.cniekirk.traintimes.domain.usecase.GetDepartureBoardUseCase
import me.cniekirk.traintimes.domain.usecase.GetStationsUseCase

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UseCaseModule::class]
)
class FakeUseCaseModule {

    @Provides
    fun provideGetDepartureBoardUseCase(fakeGetDepartureBoardUseCase: FakeGetDepartureBoardUseCase): GetDepartureBoardUseCase
        = fakeGetDepartureBoardUseCase

    @Provides
    fun provideGetStationsUseCase(fakeGetStationsUseCase: FakeGetStationsUseCase): GetStationsUseCase
        = fakeGetStationsUseCase
}