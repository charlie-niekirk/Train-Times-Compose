package me.cniekirk.traintimes.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Destination(
    @Json(name = "crs")
    val crs: String? = null,
    @Json(name = "futureChangeTo")
    val futureChangeTo: Int? = null,
    @Json(name = "futureChangeToSpecified")
    val futureChangeToSpecified: Boolean? = null,
    @Json(name = "isOperationalEndPoint")
    val isOperationalEndPoint: Boolean? = null,
    @Json(name = "locationName")
    val locationName: String? = null,
    @Json(name = "tiploc")
    val tiploc: String? = null,
    @Json(name = "via")
    val via: String? = null
)