package me.cniekirk.traintimes.features.search.mvi

import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import me.cniekirk.traintimes.features.search.model.Station

data class SearchState(
    val departureStation: Station = Station("Departure station", ""),
    val destinationStation: Station = Station("Arrival station", ""),
    val results: DepartureBoard? = null
)