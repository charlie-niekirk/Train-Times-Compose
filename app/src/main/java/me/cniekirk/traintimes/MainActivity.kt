package me.cniekirk.traintimes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.features.search.ui.SearchScreen
import me.cniekirk.traintimes.features.search.ui.SearchViewModel
import me.cniekirk.traintimes.features.serviceDetail.ui.ServiceDetailViewModel
import me.cniekirk.traintimes.features.serviceDetail.ui.ServiceDetailsScreen
import me.cniekirk.traintimes.features.stationSearch.ui.StationSearchScreen
import me.cniekirk.traintimes.features.stationSearch.ui.StationSearchViewModel
import me.cniekirk.traintimes.navigation.ObserveResult
import me.cniekirk.traintimes.navigation.Screen
import me.cniekirk.traintimes.ui.theme.TrainTimesTheme

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = listOf(
            Screen.Search,
            Screen.RoutePlanner,
            Screen.Settings
        )

        setContent {
            val navController = rememberAnimatedNavController()
            TrainTimesTheme {
                AppRoot(navController, items)
            }
        }
    }

    @Composable
    fun AppRoot(navController: NavHostController, items: List<Screen>) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = { NavBar(navController, items) }
        ) { innerPadding ->
            AnimatedNavHost(navController, startDestination = Screen.Search.route, Modifier.padding(innerPadding)) {
                composable(
                    Screen.Search.route,
                    enterTransition = {
                        when (targetState.destination.route) {
                            Screen.StationSearch.route -> {
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(300))
                            }
                            else -> null
                        }
                    },
//                            exitTransition = {
//                                when (targetState.destination.route) {
//                                    Screen.StationSearch.route -> {
//                                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(300))
//                                    }
//                                    else -> null
//                                }
//                            },
//                            popEnterTransition = {
//                                when (initialState.destination.route) {
//                                    Screen.StationSearch.route ->
//                                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(300))
//                                    else -> null
//                                }
//                            },
                    popExitTransition = {
                        when (targetState.destination.route) {
                            Screen.StationSearch.route ->
                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(300))
                            else -> null
                        }
                    }
                ) {
                    val searchViewModel = hiltViewModel<SearchViewModel>()
                    SearchScreen(
                        searchViewModel,
                        { navController.navigate(Screen.StationSearch.route.replace("{stationType}", "DepSelectedStation")) },
                        { navController.navigate(Screen.StationSearch.route.replace("{stationType}", "DestSelectedStation")) },
                        { navController.navigate(Screen.ServiceDetails.createRoute(it)) }
                    )
                    navController.ObserveResult<Station>(LocalContext.current.getString(R.string.departure_selected_station_key)) { station ->
                        station?.let {
                            searchViewModel.onDepartureChanged(it)
                        }
                    }

                    navController.ObserveResult<Station>(LocalContext.current.getString(R.string.destination_selected_station_key)) { station ->
                        station?.let {
                            searchViewModel.onArrivalChanged(it)
                        }
                    }
                }
                composable(
                    Screen.StationSearch.route,
                    enterTransition = {
                        when (initialState.destination.route) {
                            Screen.Search.route -> {
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, tween(300))
                            }
                            else -> null

                        }
                    },
                    exitTransition = {
                        when (initialState.destination.route) {
                            Screen.Search.route -> {
                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, tween(300))
                            }
                            else -> null
                        }
                    },
                    popEnterTransition = {
                        when (targetState.destination.route) {
                            Screen.Search.route ->
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Down, tween(300))
                            else -> null
                        }
                    },
                    popExitTransition = {
                        when (targetState.destination.route) {
                            Screen.Search.route ->
                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, tween(300))
                            else -> null
                        }
                    }
                ) { backStackEntry ->
                    val viewModel = hiltViewModel<StationSearchViewModel>()
                    StationSearchScreen(navController, viewModel, backStackEntry.arguments?.getString("stationType")!!)
                }
                composable(
                    Screen.ServiceDetails.route,
                    enterTransition = {
                        when (initialState.destination.route) {
                            Screen.Search.route -> {
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, tween(300))
                            }
                            else -> null

                        }
                    },
                    exitTransition = {
                        when (initialState.destination.route) {
                            Screen.Search.route -> {
                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, tween(300))
                            }
                            else -> null
                        }
                    },
                    popEnterTransition = {
                        when (targetState.destination.route) {
                            Screen.Search.route ->
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Down, tween(300))
                            else -> null
                        }
                    },
                    popExitTransition = {
                        when (targetState.destination.route) {
                            Screen.Search.route ->
                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, tween(300))
                            else -> null
                        }
                    }
                ) { backStackEntry ->
                    val viewModel = hiltViewModel<ServiceDetailViewModel>()
                    ServiceDetailsScreen(navController, viewModel, backStackEntry.arguments?.getString("serviceId")!!)
                }
//                        composable(
//                            Screen.Settings.route,
//                            enterTransition = {
//                                when (targetState.destination.route) {
//                                    Screen.Search.route -> {
//                                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(700))
//                                    }
//                                    else -> null
//                                }
//                            },
//                            exitTransition = {
//                                when (targetState.destination.route) {
//                                    Screen.Search.route -> {
//                                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(700))
//                                    }
//                                    else -> null
//                                }
//                            },
//                            popEnterTransition = {
//                                when (initialState.destination.route) {
//                                    Screen.Search.route ->
//                                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(700))
//                                    else -> null
//                                }
//                            },
//                            popExitTransition = {
//                                when (targetState.destination.route) {
//                                    Screen.Search.route ->
//                                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(700))
//                                    else -> null
//                                }
//                            }
//                        ) {
//                            val viewModel = hiltViewModel<SearchViewModel>()
//                            SearchScreen(navController, viewModel)
//                        }
            }
        }
    }

    @Composable
    fun NavBar(navController: NavController, items: List<Screen>) {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                NavigationBarItem(
                    icon = { Icon(screen.icon, contentDescription = null) },
                    label = { Text(stringResource(screen.name)) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Preview
    @Composable
    fun NavBarPreview() {
        val items = listOf(
            Screen.Search,
            Screen.RoutePlanner,
            Screen.Settings
        )
        val navController = rememberNavController()

        NavBar(navController, items)
    }

    @Preview
    @Composable
    fun NavRootPreview() {
        val items = listOf(
            Screen.Search,
            Screen.RoutePlanner,
            Screen.Settings
        )
        val navController = rememberNavController()

        AppRoot(navController, items)
    }
}