package me.cniekirk.traintimes.features.stationsearch.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavController
import me.cniekirk.traintimes.features.search.ui.SearchViewModel
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

}

private fun handleSideEffect(navController: NavController, stationSearchSideEffect: StationSearchSideEffect) {
    when (stationSearchSideEffect) {
        StationSearchSideEffect.StationSearchSelected -> {
            // TODO: Add args to savedStateHandle
            navController.popBackStack()
        }
    }
}