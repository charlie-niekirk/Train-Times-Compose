package me.cniekirk.traintimes.data.remote.service

import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NationalRailService {

    @GET("staffdepartures/{crs}")
    suspend fun getDepartures(
        @Path("crs") departureStation: String
    ): Response<DepartureBoard>

    @GET("staffdepartures/{dep}/to/{arr}")
    suspend fun getDepartures(
        @Path("dep") departureStation: String,
        @Path("arr") arrivalStation: String
    ): Response<DepartureBoard>
}