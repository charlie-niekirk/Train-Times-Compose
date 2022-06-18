package me.cniekirk.traintimes.domain.repository

import kotlinx.coroutines.flow.Flow
import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import me.cniekirk.traintimes.data.remote.model.ServiceDetails
import me.cniekirk.traintimes.data.remote.model.Station

interface NationalRailRepository {
    fun getStations(): Flow<List<Station>>
    fun getDepartures(departureStation: String): Flow<DepartureBoard>
    fun getDepartures(departureStation: String, arrivalStation: String): Flow<DepartureBoard>
    fun getServiceDetails(service: String): Flow<ServiceDetails>
}