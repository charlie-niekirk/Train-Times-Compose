package me.cniekirk.traintimes.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "activities")
    val activities: String? = null,
    @Json(name = "adhocAlerts")
    val adhocAlerts: Any? = null,
    @Json(name = "arrivalSource")
    val arrivalSource: Any? = null,
    @Json(name = "arrivalSourceInstance")
    val arrivalSourceInstance: Any? = null,
    @Json(name = "arrivalType")
    val arrivalType: Int? = null,
    @Json(name = "arrivalTypeSpecified")
    val arrivalTypeSpecified: Boolean? = null,
    @Json(name = "associations")
    val associations: List<Association>? = null,
    @Json(name = "ata")
    val ata: String? = null,
    @Json(name = "ataSpecified")
    val ataSpecified: Boolean? = null,
    @Json(name = "atd")
    val atd: String? = null,
    @Json(name = "atdSpecified")
    val atdSpecified: Boolean? = null,
    @Json(name = "crs")
    val crs: String? = null,
    @Json(name = "departureSource")
    val departureSource: String? = null,
    @Json(name = "departureSourceInstance")
    val departureSourceInstance: Any? = null,
    @Json(name = "departureType")
    val departureType: Int? = null,
    @Json(name = "departureTypeSpecified")
    val departureTypeSpecified: Boolean? = null,
    @Json(name = "detachFront")
    val detachFront: Boolean? = null,
    @Json(name = "eta")
    val eta: String? = null,
    @Json(name = "etaSpecified")
    val etaSpecified: Boolean? = null,
    @Json(name = "etd")
    val etd: String? = null,
    @Json(name = "etdSpecified")
    val etdSpecified: Boolean? = null,
    @Json(name = "falseDest")
    val falseDest: Any? = null,
    @Json(name = "fdTiploc")
    val fdTiploc: Any? = null,
    @Json(name = "isCancelled")
    val isCancelled: Boolean? = null,
    @Json(name = "isOperational")
    val isOperational: Boolean? = null,
    @Json(name = "isPass")
    val isPass: Boolean? = null,
    @Json(name = "lateness")
    val lateness: String? = null,
    @Json(name = "length")
    val length: Int? = null,
    @Json(name = "locationName")
    val locationName: String? = null,
    @Json(name = "platform")
    val platform: String? = null,
    @Json(name = "platformIsHidden")
    val platformIsHidden: Boolean? = null,
    @Json(name = "serviceIsSupressed")
    val serviceIsSupressed: Boolean? = null,
    @Json(name = "sta")
    val sta: String? = null,
    @Json(name = "staSpecified")
    val staSpecified: Boolean? = null,
    @Json(name = "std")
    val std: String? = null,
    @Json(name = "stdSpecified")
    val stdSpecified: Boolean? = null,
    @Json(name = "tiploc")
    val tiploc: String? = null
)