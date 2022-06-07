package me.cniekirk.traintimes.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DepartureBoard(
    @Json(name = "busServices")
    val busServices: Any? = null,
    @Json(name = "crs")
    val crs: String? = null,
    @Json(name = "ferryServices")
    val ferryServices: Any? = null,
    @Json(name = "filterLocationName")
    val filterLocationName: Any? = null,
    @Json(name = "filterType")
    val filterType: Int? = null,
    @Json(name = "filtercrs")
    val filtercrs: Any? = null,
    @Json(name = "generatedAt")
    val generatedAt: String? = null,
    @Json(name = "isTruncated")
    val isTruncated: Boolean? = null,
    @Json(name = "locationName")
    val locationName: String? = null,
    @Json(name = "nrccMessages")
    val nrccMessages: List<NrccMessage>? = null,
    @Json(name = "platformsAreHidden")
    val platformsAreHidden: Boolean? = null,
    @Json(name = "qos")
    val qos: Int? = null,
    @Json(name = "qosSpecified")
    val qosSpecified: Boolean? = null,
    @Json(name = "servicesAreUnavailable")
    val servicesAreUnavailable: Boolean? = null,
    @Json(name = "stationManager")
    val stationManager: String? = null,
    @Json(name = "stationManagerCode")
    val stationManagerCode: String? = null,
    @Json(name = "trainServices")
    val trainServices: List<TrainService>? = null
)