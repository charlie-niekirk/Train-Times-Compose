package me.cniekirk.traintimes.features.search.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.cniekirk.traintimes.data.remote.model.ServiceDetails
import me.cniekirk.traintimes.domain.usecase.GetServiceDetailsUseCase
import javax.inject.Inject

class FakeGetServiceDetailsUseCase @Inject constructor() : GetServiceDetailsUseCase {
    override fun invoke(service: String): Flow<ServiceDetails> {
        return flowOf()
    }
}