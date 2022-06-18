package me.cniekirk.traintimes.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Association(
    @Json(name = "category")
    val category: Int? = null,
    @Json(name = "destCRS")
    val destCRS: String? = null,
    @Json(name = "destTiploc")
    val destTiploc: String? = null,
    @Json(name = "destination")
    val destination: String? = null,
    @Json(name = "isCancelled")
    val isCancelled: Boolean? = null,
    @Json(name = "origin")
    val origin: String? = null,
    @Json(name = "originCRS")
    val originCRS: String? = null,
    @Json(name = "originTiploc")
    val originTiploc: String? = null,
    @Json(name = "rid")
    val rid: String? = null,
    @Json(name = "rsid")
    val rsid: Any? = null,
    @Json(name = "sdd")
    val sdd: String? = null,
    @Json(name = "trainid")
    val trainid: String? = null,
    @Json(name = "uid")
    val uid: String? = null
)