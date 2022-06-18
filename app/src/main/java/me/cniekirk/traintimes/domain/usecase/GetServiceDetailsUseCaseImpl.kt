package me.cniekirk.traintimes.domain.usecase

import me.cniekirk.traintimes.domain.repository.NationalRailRepository
import javax.inject.Inject

class GetServiceDetailsUseCaseImpl @Inject constructor(
    private val nationalRailRepository: NationalRailRepository
) : GetServiceDetailsUseCase {
    override fun invoke(service: String) = nationalRailRepository.getServiceDetails(service)
}