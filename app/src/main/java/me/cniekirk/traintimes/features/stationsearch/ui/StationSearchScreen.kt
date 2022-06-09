package me.cniekirk.traintimes.features.stationsearch.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.features.stationsearch.mvi.StationSearchSideEffect
import me.cniekirk.traintimes.features.stationsearch.mvi.StationSearchState
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StationSearchScreen(navController: NavController, viewModel: StationSearchViewModel) {
    val state = viewModel.collectAsState()
    // Handle side effects
    viewModel.collectSideEffect { sideEffect -> handleSideEffect(navController, sideEffect) }

    // Render state
    StationSearchContent(state)
}

@Composable
fun StationSearchContent(state: State<StationSearchState>) {
    if (state.value.stationList.isNotEmpty()) {

    } else {
        // Empty view
    }
}

@Composable
fun StationList(stations: List<Station>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(stations) { station ->
            StationRow(station)
        }
    }
}

@Composable
fun StationRow(station: Station) {

}

private fun handleSideEffect(navController: NavController, stationSearchSideEffect: StationSearchSideEffect) {
    when (stationSearchSideEffect) {
        is StationSearchSideEffect.StationSelected -> {
            // TODO: Add args to savedStateHandle
            navController.popBackStack()
        }
    }
}