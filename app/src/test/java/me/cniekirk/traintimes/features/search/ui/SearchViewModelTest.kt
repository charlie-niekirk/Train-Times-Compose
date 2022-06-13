package me.cniekirk.traintimes.features.search.ui

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import me.cniekirk.traintimes.data.remote.model.Station
import me.cniekirk.traintimes.data.remote.model.TrainService
import me.cniekirk.traintimes.domain.usecase.GetDepartureBoardUseCase
import me.cniekirk.traintimes.features.search.mvi.SearchSideEffect
import me.cniekirk.traintimes.features.search.mvi.SearchState
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val station = Station("PAD", "London Paddington")
    private val getDepartureBoardUseCase = mockk<GetDepartureBoardUseCase>()
    private val depResponse = DepartureBoard(trainServices = listOf(TrainService(departureSource = "Darwin")))
    private val depDestResponse = DepartureBoard(trainServices = listOf(TrainService(departureSource = "TRUST")))

    private lateinit var underTest: SearchViewModel

    @Before
    fun setUp() {
        underTest = SearchViewModel(getDepartureBoardUseCase)
        every { getDepartureBoardUseCase(any()) } returns flowOf(depResponse)
        every { getDepartureBoardUseCase(any(), any()) } returns flowOf(depDestResponse)
    }

    @Test
    fun `verify getDepartures with departureStation only reduces state with depResponse`() = runTest {
        val testSubject = underTest.test(SearchState(departureStation = station))

        testSubject.testIntent { getDepartures() }
        testSubject.assert(SearchState(departureStation = station)) {
            states(
                { copy(results = depResponse) }
            )
        }
    }

    @Test
    fun `verify getDepartures with departureStation and destinationStation reduces state with depDestResponse`() = runTest {
        val testSubject = underTest.test(SearchState(departureStation = station, destinationStation = station))

        testSubject.testIntent { getDepartures() }
        testSubject.assert(SearchState(departureStation = station, destinationStation = station)) {
            states(
                { copy(results = depDestResponse) }
            )
        }
    }

    @Test
    fun `verify getDepartures without departureStation posts NoDepartureSpecifiedError side effect`() = runTest {
        val testSubject = underTest.test()

        testSubject.testIntent { getDepartures() }
        testSubject.assert(SearchState()) {
            postedSideEffects(SearchSideEffect.NoDepartureSpecifiedError)
        }
    }

    @Test
    fun `verify onDeparturesClick posts NavigateToDepartureStationSearch side effect`() = runTest {
        val testSubject = underTest.test()

        testSubject.testIntent { onDepartureClick() }
        testSubject.assert(SearchState()) {
            postedSideEffects(SearchSideEffect.NavigateToDepartureStationSearch)
        }
    }

    @Test
    fun `verify onArrivalClick posts NavigateToArrivalStationSearch side effect`() = runTest {
        val testSubject = underTest.test()

        testSubject.testIntent { onArrivalClick() }
        testSubject.assert(SearchState()) {
            postedSideEffects(SearchSideEffect.NavigateToArrivalStationSearch)
        }
    }

    @Test
    fun `verify onDepartureChanged reduces state and sets departureStation`() = runTest {
        val testSubject = underTest.test()

        testSubject.testIntent { onDepartureChanged(station) }
        testSubject.assert(SearchState()) {
            states(
                { copy(departureStation = station) }
            )
        }
    }

    @Test
    fun `verify onArrivalChanged reduces state and sets destinationStation`() = runTest {
        val testSubject = underTest.test()

        testSubject.testIntent { onArrivalChanged(station) }
        testSubject.assert(SearchState()) {
            states(
                { copy(destinationStation = station) }
            )
        }
    }
}