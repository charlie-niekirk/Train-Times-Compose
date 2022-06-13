package me.cniekirk.traintimes.features.stationsearch.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.di.IoDispatcher
import me.cniekirk.traintimes.domain.usecase.GetStationsUseCase
import me.cniekirk.traintimes.features.stationsearch.mvi.StationSearchSideEffect
import me.cniekirk.traintimes.features.stationsearch.mvi.StationSearchState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StationSearchViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase
): ViewModel(), ContainerHost<StationSearchState, StationSearchSideEffect> {

    override val container = container<StationSearchState, StationSearchSideEffect>(StationSearchState()) {
        getStations()
    }

    private fun getStations() = intent {
        getStationsUseCase().collect {
            reduce { state.copy(stationList = it) }
        }
    }

    fun onStationSelected(station: Station) = intent {
        postSideEffect(StationSearchSideEffect.StationSelected(station))
    }
}