package me.cniekirk.traintimes.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import me.cniekirk.traintimes.domain.repository.NationalRailRepository
import javax.inject.Inject

class GetDepartureBoardUseCaseImpl @Inject constructor(
    private val nationalRailRepository: NationalRailRepository
) : GetDepartureBoardUseCase {

    override fun invoke(departureStation: String, arrivalStation: String): Flow<DepartureBoard> {
        return nationalRailRepository.getDepartures(departureStation, arrivalStation)
    }

    override fun invoke(departureStation: String): Flow<DepartureBoard> {
        return nationalRailRepository.getDepartures(departureStation)
    }
}