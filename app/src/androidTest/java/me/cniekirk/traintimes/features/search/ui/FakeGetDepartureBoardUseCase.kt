package me.cniekirk.traintimes.features.search.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import me.cniekirk.traintimes.data.remote.model.Origin
import me.cniekirk.traintimes.data.remote.model.TrainService
import me.cniekirk.traintimes.domain.usecase.GetDepartureBoardUseCase
import javax.inject.Inject

class FakeGetDepartureBoardUseCase @Inject constructor() : GetDepartureBoardUseCase {
    override fun invoke(departureStation: String, arrivalStation: String): Flow<DepartureBoard> {
        return flowOf(DepartureBoard(trainServices = listOf(TrainService(origin = listOf(Origin(locationName = "London Paddington"))))))
    }

    override fun invoke(departureStation: String): Flow<DepartureBoard> {
        return flowOf(DepartureBoard(trainServices = listOf(TrainService(origin = listOf(Origin(locationName = "London Paddington"))))))
    }
}