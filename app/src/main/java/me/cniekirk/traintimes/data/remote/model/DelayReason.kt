package me.cniekirk.traintimes.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DelayReason(
    @Json(name = "near")
    val near: Boolean? = null,
    @Json(name = "tiploc")
    val tiploc: String? = null,
    @Json(name = "value")
    val value: Int? = null
)