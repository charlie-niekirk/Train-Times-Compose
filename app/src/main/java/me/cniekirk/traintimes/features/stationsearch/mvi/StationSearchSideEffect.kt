package me.cniekirk.traintimes.features.stationsearch.mvi

import me.cniekirk.traintimes.data.remote.model.Station

sealed class StationSearchSideEffect {
    data class StationSelected(val station: Station) : StationSearchSideEffect()
}