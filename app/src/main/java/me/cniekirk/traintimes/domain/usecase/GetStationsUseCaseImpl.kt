package me.cniekirk.traintimes.domain.usecase

import me.cniekirk.traintimes.domain.repository.NationalRailRepository
import javax.inject.Inject

class GetStationsUseCaseImpl @Inject constructor(
    private val nationalRailRepository: NationalRailRepository
) : GetStationsUseCase {

    override fun invoke() = nationalRailRepository.getStations()
}