package me.cniekirk.traintimes.features.stationsearch.mvi

import me.cniekirk.traintimes.data.remote.model.Station

data class StationSearchState(
    val stationList: List<Station> = emptyList(),
    val isLoading: Boolean = true
)