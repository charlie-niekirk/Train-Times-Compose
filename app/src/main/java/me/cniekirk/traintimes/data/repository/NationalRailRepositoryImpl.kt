package me.cniekirk.traintimes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.data.remote.service.NationalRailService
import me.cniekirk.traintimes.domain.repository.NationalRailRepository
import javax.inject.Inject

class NationalRailRepositoryImpl @Inject constructor(
    private val nationalRailService: NationalRailService
) : NationalRailRepository {

    override fun getStations(): Flow<List<Station>> = flow {
        val response = nationalRailService.getStations()
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        }
    }

    override fun getDepartures(departureStation: String): Flow<DepartureBoard> = flow {
        val response = nationalRailService.getDepartures(departureStation)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        }
    }

    override fun getDepartures(departureStation: String, arrivalStation: String): Flow<DepartureBoard> = flow {
        val response = nationalRailService.getDepartures(departureStation, arrivalStation)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        }
    }
}