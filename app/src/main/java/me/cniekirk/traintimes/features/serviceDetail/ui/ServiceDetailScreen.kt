package me.cniekirk.traintimes.features.serviceDetail.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.cniekirk.traintimes.data.remote.model.Location
import me.cniekirk.traintimes.features.serviceDetail.mvi.ServiceDetailState
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ServiceDetailsScreen(navController: NavController, viewModel: ServiceDetailViewModel, serviceId: String) {
    val state = viewModel.collectAsState()

    ServiceDetailsContent(state)
    viewModel.getService(serviceId)
}

@Composable
fun ServiceDetailsContent(state: State<ServiceDetailState>) {
    if (state.value.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        state.value.serviceDetails?.let {
            Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
                ServiceProgress(stopList = it.locations ?: emptyList())
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ServiceProgressPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(16.dp))
            ServiceProgress(stopList = listOf(
                Location(locationName = "London Paddington"),
                Location(locationName = "Newbury"),
                Location(locationName = "Pewsey"),
                Location(locationName = "Westbury")
            ))
        }
    }
}

@Composable
fun ServiceProgress(stopList: List<Location>) {
    val filtered = stopList.filter { it.isPass == false }
    Column(modifier = Modifier.fillMaxSize()) {
        filtered.forEachIndexed { index, location ->
            when (index) {
                0 -> {
                    FirstStop(location)
                }
                filtered.lastIndex -> {
                    LastStop(location)
                }
                else -> {
                    MiddleStop(location)
                }
            }
        }
    }
}

@Composable
fun FirstStop(location: Location) {
    Box(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth()) {
        Canvas(modifier = Modifier
            .size(width = 8.dp, height = 25.dp)
            .align(Alignment.BottomStart), onDraw = {
            drawLine(
                color = Color.LightGray,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                strokeWidth = 3f
            )
            drawLine(
                Color.LightGray,
                start = Offset(x = size.width / 2, y = 0f),
                end = Offset(x = size.width / 2, y = size.height),
                strokeWidth = 3f
            )
        })
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(text = location.locationName ?: "")
                Text(
                    text = "Platform ${location.platform ?: "?"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun MiddleStop(location: Location) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(width = 8.dp, height = 50.dp), onDraw = {
            drawLine(
                color = Color.LightGray,
                start = Offset(x = size.width / 2, y = size.height / 2),
                end = Offset(x = size.width, y = size.height / 2),
                strokeWidth = 3f
            )
            drawLine(
                Color.LightGray,
                start = Offset(x = size.width / 2, y = 0f),
                end = Offset(x = size.width / 2, y = size.height),
                strokeWidth = 3f
            )
        })
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = location.locationName ?: "")
            Text(
                text = "Platform ${location.platform ?: "?"}",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun LastStop(location: Location) {
    Box(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth()) {
        Canvas(modifier = Modifier
            .size(width = 8.dp, height = 25.dp)
            .align(Alignment.TopStart), onDraw = {
            drawLine(
                color = Color.LightGray,
                start = Offset(x = 0f, y = size.height),
                end = Offset(x = size.width, y = size.height),
                strokeWidth = 3f
            )
            drawLine(
                Color.LightGray,
                start = Offset(x = size.width / 2, y = 0f),
                end = Offset(x = size.width / 2, y = size.height),
                strokeWidth = 3f
            )
        })
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(text = location.locationName ?: "")
                Text(
                    text = "Platform ${location.platform ?: "?"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}