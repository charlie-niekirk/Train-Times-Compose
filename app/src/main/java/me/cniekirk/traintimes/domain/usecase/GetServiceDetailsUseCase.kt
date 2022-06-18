package me.cniekirk.traintimes.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.cniekirk.traintimes.data.remote.model.ServiceDetails

interface GetServiceDetailsUseCase {
    operator fun invoke(service: String): Flow<ServiceDetails>
}