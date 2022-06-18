package me.cniekirk.traintimes.data.remote.service

import me.cniekirk.traintimes.data.remote.model.DepartureBoard
import me.cniekirk.traintimes.data.remote.model.ServiceDetails
import me.cniekirk.traintimes.data.remote.model.Station
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NationalRailService {

    @GET("crs")
    suspend fun getStations(): Response<List<Station>>

    @GET("staffdepartures/{crs}")
    suspend fun getDepartures(
        @Path("crs") departureStation: String
    ): Response<DepartureBoard>

    @GET("staffdepartures/{dep}/to/{arr}")
    suspend fun getDepartures(
        @Path("dep") departureStation: String,
        @Path("arr") arrivalStation: String
    ): Response<DepartureBoard>

    @GET("service/{service}")
    suspend fun getServiceDetails(
        @Path("service") service: String
    ): Response<ServiceDetails>
}