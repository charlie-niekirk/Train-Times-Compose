package me.cniekirk.traintimes.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import me.cniekirk.traintimes.R

sealed class Screen(val route: String, @StringRes val name: Int, val icon: ImageVector) {
    object Search : Screen(route = "search", R.string.search, Icons.Filled.Search)
    object StationSearch : Screen(route = "stationSearch", R.string.station_search, Icons.Filled.Settings)
    object RoutePlanner : Screen(route = "routePlanner", R.string.route_planner, Icons.Filled.Add)
    object TrainListing : Screen(route = "trainListing/{}", R.string.listing, Icons.Filled.Settings)
    object Settings : Screen(route = "settings", R.string.settings, Icons.Filled.Settings)
    object ServiceDetails : Screen(route = "details/{id}", R.string.details, Icons.Filled.Settings) {
        fun createRoute(id: String) = "details/$id"
    }
}
