package me.cniekirk.traintimes.domain.usecase

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import me.cniekirk.traintimes.domain.repository.NationalRailRepository
import java.text.SimpleDateFormat
import javax.inject.Inject

class GetDepartureBoardUseCaseImpl @Inject constructor(
    private val nationalRailRepository: NationalRailRepository
) : GetDepartureBoardUseCase {

    override fun invoke(departureStation: String, arrivalStation: String): Flow<DepartureBoard> {
        return nationalRailRepository.getDepartures(departureStation, arrivalStation).map {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val output = SimpleDateFormat("HH:mm")
            val services = it.trainServices?.map { trainService ->
                val parsedStd = sdf.parse(trainService.std)
                val parsedEtd = sdf.parse(trainService.etd)
                trainService.copy(std = output.format(parsedStd), etd = output.format(parsedEtd))
            }
            it.copy(trainServices = services)
        }
    }

    override fun invoke(departureStation: String): Flow<DepartureBoard> {
        return nationalRailRepository.getDepartures(departureStation).map {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val output = SimpleDateFormat("HH:mm")
            val services = it.trainServices?.map { trainService ->
                val parsedStd = sdf.parse(trainService.std)
                val parsedEtd = sdf.parse(trainService.etd)
                trainService.copy(std = output.format(parsedStd), etd = output.format(parsedEtd))
            }
            it.copy(trainServices = services)
        }
    }
}

inline fun <T> MutableList<T>.mapInPlace(mutator: (T)->T) {
    val iterate = this.listIterator()
    while (iterate.hasNext()) {
        val oldValue = iterate.next()
        val newValue = mutator(oldValue)
        if (newValue !== oldValue) {
            iterate.set(newValue)
        }
    }
}