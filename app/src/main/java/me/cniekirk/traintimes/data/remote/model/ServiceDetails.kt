package me.cniekirk.traintimes.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServiceDetails(
    @Json(name = "cancelReason")
    val cancelReason: Any? = null,
    @Json(name = "category")
    val category: String? = null,
    @Json(name = "delayReason")
    val delayReason: DelayReason? = null,
    @Json(name = "formation")
    val formation: Any? = null,
    @Json(name = "generatedAt")
    val generatedAt: String? = null,
    @Json(name = "isCharter")
    val isCharter: Boolean? = null,
    @Json(name = "isPassengerService")
    val isPassengerService: Boolean? = null,
    @Json(name = "isReverseFormation")
    val isReverseFormation: Boolean? = null,
    @Json(name = "locations")
    val locations: List<Location>? = null,
    @Json(name = "operator")
    val `operator`: String? = null,
    @Json(name = "operatorCode")
    val operatorCode: String? = null,
    @Json(name = "rid")
    val rid: String? = null,
    @Json(name = "rsid")
    val rsid: Any? = null,
    @Json(name = "sdd")
    val sdd: String? = null,
    @Json(name = "serviceType")
    val serviceType: Int? = null,
    @Json(name = "trainid")
    val trainid: String? = null,
    @Json(name = "uid")
    val uid: String? = null
)