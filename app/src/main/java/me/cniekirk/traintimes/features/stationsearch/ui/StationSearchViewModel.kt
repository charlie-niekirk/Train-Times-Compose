package me.cniekirk.traintimes.features.stationsearch.ui

import androidx.lifecycle.ViewModel
import me.cniekirk.traintimes.features.stationsearch.mvi.StationSearchSideEffect
import me.cniekirk.traintimes.features.stationsearch.mvi.StationSearchState
import org.orbitmvi.orbit.ContainerHost
import javax.inject.Inject

class StationSearchViewModel @Inject constructor(

): ViewModel(), ContainerHost<StationSearchState, StationSearchSideEffect> {
}