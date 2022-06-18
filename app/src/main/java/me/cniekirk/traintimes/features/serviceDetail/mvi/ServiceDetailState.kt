package me.cniekirk.traintimes.features.serviceDetail.mvi

import me.cniekirk.traintimes.data.remote.model.ServiceDetails

data class ServiceDetailState(
    val serviceDetails: ServiceDetails? = null,
    val isLoading: Boolean = true
)