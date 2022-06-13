package me.cniekirk.traintimes.features.search.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import me.cniekirk.traintimes.data.remote.model.Origin
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.data.remote.model.TrainService
import me.cniekirk.traintimes.features.search.mvi.SearchState
import me.cniekirk.traintimes.ui.theme.TrainTimesTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltAndroidTest
class SearchScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createComposeRule()

    @Inject
    lateinit var getDepartureBoardUseCase: FakeGetDepartureBoardUseCase

    private var searchViewModel: SearchViewModel? = null
    private val fakeInitialState = mutableStateOf(SearchState())
    private val fakePopulatedState = mutableStateOf(
        SearchState(
            results = DepartureBoard(
                trainServices = listOf(
                    TrainService(origin = listOf(
                            Origin(locationName = "London Paddington")
                        )
                    ),
                    TrainService(origin = listOf(
                            Origin(locationName = "London Waterloo")
                        )
                    )
                )
            )
        )
    )

    @Before
    fun setUp() {
        hiltRule.inject()
        searchViewModel = SearchViewModel(getDepartureBoardUseCase)
    }

    @Test
    fun verifySearchClick_InvokesUseCase_AndShowsOneResult() {
        // Given
        composeRule.setContent {
            TrainTimesTheme {
                SearchScreen(
                    viewModel = searchViewModel!!,
                    navigateToDepartureSelection = {},
                    navigateToDestinationSelection = {}
                )
            }
        }

        // When
        searchViewModel?.onDepartureChanged(Station("PAD", "London Paddington"))
        composeRule.onNodeWithTag("searchBtn").performClick()

        // Then
        composeRule.onAllNodesWithTag("DepRow").assertCountEquals(1)
    }

    @Test
    fun verifyInitialState_DisplaysNoListItems() {
        composeRule.setContent {
            TrainTimesTheme {
                SearchContent(state = fakeInitialState, onSearch = {}, onDepartureClick = {}, onArrivalClick = {})
            }
        }

        composeRule.onAllNodesWithTag("DepRow").assertCountEquals(0)
    }

    @Test
    fun verifyPopulatedState_DisplaysTwoListItems() {
        composeRule.setContent {
            TrainTimesTheme {
                SearchContent(state = fakePopulatedState, onSearch = {}, onDepartureClick = {}, onArrivalClick = {})
            }
        }

        composeRule.onAllNodesWithTag("DepRow").assertCountEquals(2)
    }
}