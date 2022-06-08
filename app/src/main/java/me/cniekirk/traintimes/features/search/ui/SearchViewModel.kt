package me.cniekirk.traintimes.features.search.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
        if (state.departureStation.crsCode.isNotEmpty()) {
            getDepartureBoardUseCase(state.departureStation.crsCode).collect {
                reduce { state.copy(results = it) }
            }
        }
    }

    fun onDepartureClick() = intent {
        postSideEffect(SearchSideEffect.NavigateToDepartureStationSearch)
    }

    fun onArrivalClick() = intent {
        postSideEffect(SearchSideEffect.NavigateToArrivalStationSearch)
    }
}