package me.cniekirk.traintimes.features.serviceDetail.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.cniekirk.traintimes.domain.usecase.GetServiceDetailsUseCase
import me.cniekirk.traintimes.features.serviceDetail.mvi.ServiceDetailSideEffect
import me.cniekirk.traintimes.features.serviceDetail.mvi.ServiceDetailState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ServiceDetailViewModel @Inject constructor(
    private val getServiceDetailsUseCase: GetServiceDetailsUseCase
) : ContainerHost<ServiceDetailState, ServiceDetailSideEffect>, ViewModel() {

    override val container = container<ServiceDetailState, ServiceDetailSideEffect>(ServiceDetailState())

    fun getService(service: String) = intent {
        getServiceDetailsUseCase(service).collect {
            reduce { state.copy(serviceDetails = it, isLoading = false) }
        }
    }
}