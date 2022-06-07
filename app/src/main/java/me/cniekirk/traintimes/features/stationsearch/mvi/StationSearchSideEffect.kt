package me.cniekirk.traintimes.features.stationsearch.mvi

sealed class StationSearchSideEffect {
    object StationSearchSelected : StationSearchSideEffect()
}