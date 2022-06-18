package me.cniekirk.traintimes.features.stationSearch.ui

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.domain.usecase.GetStationsUseCase
import me.cniekirk.traintimes.features.stationSearch.mvi.StationSearchSideEffect
import me.cniekirk.traintimes.features.stationSearch.mvi.StationSearchState
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.test

@ExperimentalCoroutinesApi
class StationSearchViewModelTest {

    private val getStationsUseCase = mockk<GetStationsUseCase>()
    private val fakeStation = Station("PAD", "London Paddington")
    private val stationsResponse = listOf(fakeStation)

    private lateinit var underTest: StationSearchViewModel

    @Before
    fun setup() {
        underTest = StationSearchViewModel(getStationsUseCase)
        every { getStationsUseCase() } returns flowOf(stationsResponse)
    }

    @Test
    fun `verify getStations reduces state with stationsResponse`() = runTest {
        val testSubject = underTest.test(StationSearchState())

        testSubject.runOnCreate()
        testSubject.assert(StationSearchState()) {
            states(
                { copy(stationList = stationsResponse) }
            )
        }
    }

    @Test
    fun `verify onStationSelected posts StationSelected side effect`() = runTest {
        val testSubject = underTest.test(StationSearchState())

        testSubject.testIntent { onStationSelected(fakeStation) }
        testSubject.assert(StationSearchState()) {
            postedSideEffects(StationSearchSideEffect.StationSelected(fakeStation))
        }
    }
}