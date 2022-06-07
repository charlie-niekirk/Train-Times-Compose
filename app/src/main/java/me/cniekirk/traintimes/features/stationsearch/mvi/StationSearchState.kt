package me.cniekirk.traintimes.features.stationsearch.mvi

data class StationSearchState(
    val stationList: List<String> = emptyList()
)