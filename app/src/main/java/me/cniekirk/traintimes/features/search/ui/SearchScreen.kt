package me.cniekirk.traintimes.features.search.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.cniekirk.traintimes.R
import me.cniekirk.traintimes.features.search.mvi.SearchSideEffect
import me.cniekirk.traintimes.features.search.mvi.SearchState
import me.cniekirk.traintimes.navigation.Screen
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel) {
    val state = viewModel.collectAsState()
    viewModel.collectSideEffect { handleSideEffect(navController, it) }

    SearchContent(
        state,
        { viewModel.getDepartures() },
        { viewModel.onDepartureClick() },
        { viewModel.onArrivalClick() }
    )
}

@Composable
fun SearchContent(
    state: State<SearchState>,
    onSearch: () -> Unit,
    onDepartureClick: () -> Unit,
    onArrivalClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Search",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        SelectableItem(
            text = state.value.departureStation.name,
            modifier = Modifier.padding(top = 16.dp),
            onDepartureClick
        )
        SelectableItem(
            text = state.value.destinationStation.name,
            modifier = Modifier.padding(top = 12.dp),
            onArrivalClick
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            onClick = { onSearch() }
        ) {
            Text(stringResource(R.string.search))
        }
    }
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
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun SearchContentPreview() {
    val state = mutableStateOf(SearchState())
    SearchContent(state, {}, {}, {})
}

private fun handleSideEffect(navController: NavController, searchSideEffect: SearchSideEffect) {
    when (searchSideEffect) {
        is SearchSideEffect.NavigateToResults -> {
            navController.navigate(Screen.TrainListing.route)
        }
        SearchSideEffect.NavigateToArrivalStationSearch -> {
            navController.navigate(Screen.StationSearch.route)
        }
        SearchSideEffect.NavigateToDepartureStationSearch -> {
            navController.navigate(Screen.StationSearch.route)
        }
    }
}