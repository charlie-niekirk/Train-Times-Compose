package me.cniekirk.traintimes.features.search.ui

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.cniekirk.traintimes.R
import me.cniekirk.traintimes.data.remote.model.TrainService
import me.cniekirk.traintimes.features.search.mvi.SearchSideEffect
import me.cniekirk.traintimes.features.search.mvi.SearchState
import me.cniekirk.traintimes.ui.util.DevicesPreview
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToDepartureSelection: () -> Unit,
    navigateToDestinationSelection: () -> Unit,
    navigateToServiceDetails: (String) -> Unit
) {
    val state = viewModel.collectAsState()
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(context, sideEffect, navigateToDepartureSelection, navigateToDestinationSelection, navigateToServiceDetails)
    }

    SearchContent(
        state,
        { viewModel.getDepartures() },
        { viewModel.onDepartureClick() },
        { viewModel.onArrivalClick() },
        { viewModel.onServiceClick(it) }
    )
}

@Composable
fun SearchContent(
    state: State<SearchState>,
    onSearch: () -> Unit,
    onDepartureClick: () -> Unit,
    onArrivalClick: () -> Unit,
    onServiceClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Search",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        SelectableItem(
            text = state.value.departureStation.stationName,
            modifier = Modifier.padding(top = 16.dp),
            onDepartureClick
        )
        SelectableItem(
            text = state.value.destinationStation.stationName,
            modifier = Modifier.padding(top = 12.dp),
            onArrivalClick
        )
        DeparturesList(
            departures = state.value.results?.trainServices ?: emptyList(),
            modifier = Modifier
                .padding(top = 16.dp)
                .weight(1f),
            onClick = { onServiceClick(it.rid ?: "") }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
                .testTag("searchBtn"),
            onClick = { onSearch() }
        ) {
            Text(stringResource(R.string.search))
        }
    }
}

@Composable
fun DeparturesList(departures: List<TrainService>, modifier: Modifier = Modifier, onClick: (TrainService) -> Unit) {
    LazyColumn(modifier) {
        items(departures) { departure ->
            DepartureRow(departure, onClick, modifier = Modifier.testTag("DepRow"))
        }
    }
}

@Composable
fun DepartureRow(departure: TrainService, onClick: (TrainService) -> Unit, modifier: Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick(departure) }) {
        Text(
            text = "${departure.etd}",
            Modifier.padding(top = 16.dp, start = 16.dp)
        )
        Column(Modifier.padding(start = 16.dp)) {
            Text(
                text = "to ${departure.destination?.first()?.locationName}",
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Platform ${departure.platform ?: "unknown"}",
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
    Divider()
}

@Composable
fun SelectableItem(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(8.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .clip(shape)
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick() },
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, start = 16.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@DevicesPreview
@Composable
fun SearchContentPreview() {
    val state = mutableStateOf(SearchState())
    SearchContent(state, {}, {}, {}, {})
}

private fun handleSideEffect(
    context: Context,
    searchSideEffect: SearchSideEffect,
    navigateToDepartureSelection: () -> Unit,
    navigateToDestinationSelection: () -> Unit,
    navigateToServiceDetails: (String) -> Unit
) {
    when (searchSideEffect) {
        is SearchSideEffect.NavigateToResults -> {
            //TODO
        }
        SearchSideEffect.NavigateToArrivalStationSearch -> { navigateToDestinationSelection() }
        SearchSideEffect.NavigateToDepartureStationSearch -> { navigateToDepartureSelection() }
        SearchSideEffect.NoDepartureSpecifiedError -> {
            Toast.makeText(context, R.string.error_station_not_selected, Toast.LENGTH_SHORT).show()
        }
        is SearchSideEffect.NavigateToServiceDetails -> {
            navigateToServiceDetails(searchSideEffect.serviceId)
        }
    }
}