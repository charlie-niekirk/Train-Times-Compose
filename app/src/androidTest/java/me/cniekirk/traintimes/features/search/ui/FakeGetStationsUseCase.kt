package me.cniekirk.traintimes.features.search.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.domain.usecase.GetStationsUseCase
import javax.inject.Inject

class FakeGetStationsUseCase @Inject constructor() : GetStationsUseCase {
    override fun invoke(): Flow<List<Station>> {
        return flowOf(listOf(Station("PAD", "London Paddington")))
    }
}