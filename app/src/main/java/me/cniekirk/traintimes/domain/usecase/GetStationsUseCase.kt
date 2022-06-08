package me.cniekirk.traintimes.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.cniekirk.traintimes.data.remote.model.Station

interface GetStationsUseCase {
    operator fun invoke(): Flow<List<Station>>
}