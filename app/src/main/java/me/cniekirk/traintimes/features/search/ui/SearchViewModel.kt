package me.cniekirk.traintimes.features.search.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.domain.usecase.GetDepartureBoardUseCase
import me.cniekirk.traintimes.features.search.mvi.SearchSideEffect
import me.cniekirk.traintimes.features.search.mvi.SearchState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getDepartureBoardUseCase: GetDepartureBoardUseCase
) : ViewModel(), ContainerHost<SearchState, SearchSideEffect> {

    override val container = container<SearchState, SearchSideEffect>(SearchState())

    fun getDepartures() = intent {
        println("HERE")
        if (state.departureStation.crsCode.isNotEmpty()) {
            println("HERE 1")
            if (state.destinationStation.crsCode.isNotEmpty()) {
                println("HERE 2")
                getDepartureBoardUseCase(state.departureStation.crsCode, state.destinationStation.crsCode).collect {
                    reduce { state.copy(results = it) }
                }
            } else {
                println("HERE 3")
                getDepartureBoardUseCase(state.departureStation.crsCode).collect {
                    println("HERE 4")
                    reduce { state.copy(results = it) }
                }
            }
        } else {
            println("HERE 5")
            postSideEffect(SearchSideEffect.NoDepartureSpecifiedError)
        }
    }

    fun onDepartureClick() = intent {
        postSideEffect(SearchSideEffect.NavigateToDepartureStationSearch)
    }

    fun onArrivalClick() = intent {
        postSideEffect(SearchSideEffect.NavigateToArrivalStationSearch)
    }

    fun onDepartureChanged(station: Station) = intent {
        reduce { state.copy(departureStation = station) }
    }

    fun onArrivalChanged(station: Station) = intent {
        reduce { state.copy(destinationStation = station) }
    }

    fun onServiceClick(serviceId: String) = intent {
        postSideEffect(SearchSideEffect.NavigateToServiceDetails(serviceId))
    }
}