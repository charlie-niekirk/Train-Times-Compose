package me.cniekirk.traintimes.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Station(
    @Json(name = "crsCode")
    val crsCode: String,
    @Json(name = "stationName")
    val stationName: String
)