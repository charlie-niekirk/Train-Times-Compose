package me.cniekirk.traintimes.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.cniekirk.traintimes.data.remote.model.DepartureBoard

interface GetDepartureBoardUseCase {
    operator fun invoke(departureStation: String, arrivalStation: String): Flow<DepartureBoard>
    operator fun invoke(departureStation: String): Flow<DepartureBoard>
}