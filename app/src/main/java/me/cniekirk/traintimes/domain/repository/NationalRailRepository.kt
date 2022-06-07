package me.cniekirk.traintimes.domain.repository

import kotlinx.coroutines.flow.Flow
import me.cniekirk.traintimes.data.remote.model.DepartureBoard

interface NationalRailRepository {

    fun getDepartures(departureStation: String): Flow<DepartureBoard>
    fun getDepartures(departureStation: String, arrivalStation: String): Flow<DepartureBoard>
}