package me.cniekirk.traintimes.features.stationSearch.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.cniekirk.traintimes.R
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.features.stationSearch.mvi.StationSearchSideEffect
import me.cniekirk.traintimes.features.stationSearch.mvi.StationSearchState
import me.cniekirk.traintimes.ui.util.DevicesPreview
import me.cniekirk.traintimes.ui.util.M3TextField
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun StationSearchScreen(navController: NavController, viewModel: StationSearchViewModel, key: String) {
    val state = viewModel.collectAsState()
    // Handle side effects
    viewModel.collectSideEffect { sideEffect -> handleSideEffect(navController, sideEffect, key) }
    // Render state
    StationSearchContent(state) {
        viewModel.onStationSelected(it)
    }
}

@Composable
fun StationSearchContent(state: State<StationSearchState>, onStationClick: (Station) -> Unit) {
    if (state.value.stationList.isNotEmpty()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.stations_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
            M3TextField(
                onValueChanged = {
                    // Filter the list
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                leadingIcon = Icons.Default.Search
            )
            StationList(
                stations = state.value.stationList,
                modifier = Modifier.padding(top = 16.dp),
                onClick = onStationClick
            )
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.stations_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
            CircularProgressIndicator()
        }
    }
}

@Composable
fun StationList(stations: List<Station>, modifier: Modifier = Modifier, onClick: (Station) -> Unit) {
    LazyColumn(modifier) {
        items(stations) { station ->
            StationRow(station, onClick)
        }
    }
}

@Composable
fun StationRow(station: Station, onClick: (Station) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(station) }) {
        Text(
            text = station.stationName,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
        )
    }
}

private fun handleSideEffect(
    navController: NavController,
    stationSearchSideEffect: StationSearchSideEffect,
    key: String
) {
    when (stationSearchSideEffect) {
        is StationSearchSideEffect.StationSelected -> {
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set(key, stationSearchSideEffect.station)
            navController.popBackStack()
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@DevicesPreview
@Composable
fun StationSearchContentPreview() {
    val state = mutableStateOf(StationSearchState(listOf(
        Station("PAD", "London Paddington"),
        Station("LBG", "London Bridge"),
        Station("CNS", "London Cannon Street")

    ), false))
    StationSearchContent(state) {}
}