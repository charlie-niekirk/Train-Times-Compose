package me.cniekirk.traintimes.features.search.mvi

sealed class SearchSideEffect {
    object NavigateToDepartureStationSearch : SearchSideEffect()
    object NavigateToArrivalStationSearch : SearchSideEffect()
    object NoDepartureSpecifiedError : SearchSideEffect()
    data class NavigateToResults(val departing: String, val arriving: String) : SearchSideEffect()
}