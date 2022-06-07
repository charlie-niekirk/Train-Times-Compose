package me.cniekirk.traintimes.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrainService(
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
    @Json(name = "ata")
    val ata: String? = null,
    @Json(name = "ataSpecified")
    val ataSpecified: Boolean? = null,
    @Json(name = "atd")
    val atd: String? = null,
    @Json(name = "atdSpecified")
    val atdSpecified: Boolean? = null,
    @Json(name = "cancelReason")
    val cancelReason: Any? = null,
    @Json(name = "category")
    val category: String? = null,
    @Json(name = "currentDestinations")
    val currentDestinations: Any? = null,
    @Json(name = "currentOrigins")
    val currentOrigins: Any? = null,
    @Json(name = "delayReason")
    val delayReason: Any? = null,
    @Json(name = "departureSource")
    val departureSource: String? = null,
    @Json(name = "departureSourceInstance")
    val departureSourceInstance: Any? = null,
    @Json(name = "departureType")
    val departureType: Int? = null,
    @Json(name = "departureTypeSpecified")
    val departureTypeSpecified: Boolean? = null,
    @Json(name = "destination")
    val destination: List<Destination>? = null,
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
    @Json(name = "filterLocationCancelled")
    val filterLocationCancelled: Boolean? = null,
    @Json(name = "filterLocationOperational")
    val filterLocationOperational: Boolean? = null,
    @Json(name = "formation")
    val formation: Any? = null,
    @Json(name = "isCancelled")
    val isCancelled: Boolean? = null,
    @Json(name = "isCharter")
    val isCharter: Boolean? = null,
    @Json(name = "isCircularRoute")
    val isCircularRoute: Boolean? = null,
    @Json(name = "isOperationalCall")
    val isOperationalCall: Boolean? = null,
    @Json(name = "isPassengerService")
    val isPassengerService: Boolean? = null,
    @Json(name = "isReverseFormation")
    val isReverseFormation: Boolean? = null,
    @Json(name = "length")
    val length: Int? = null,
    @Json(name = "operator")
    val operator: String? = null,
    @Json(name = "operatorCode")
    val operatorCode: String? = null,
    @Json(name = "origin")
    val origin: List<Origin>? = null,
    @Json(name = "platform")
    val platform: String? = null,
    @Json(name = "platformIsHidden")
    val platformIsHidden: Boolean? = null,
    @Json(name = "rid")
    val rid: String? = null,
    @Json(name = "rsid")
    val rsid: String? = null,
    @Json(name = "sdd")
    val sdd: String? = null,
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
    @Json(name = "trainid")
    val trainid: String? = null,
    @Json(name = "uid")
    val uid: String? = null
)