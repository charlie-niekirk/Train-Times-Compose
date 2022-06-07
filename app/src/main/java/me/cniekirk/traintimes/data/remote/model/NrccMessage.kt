package me.cniekirk.traintimes.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NrccMessage(
    @Json(name = "category")
    val category: Int? = null,
    @Json(name = "severity")
    val severity: Int? = null,
    @Json(name = "xhtmlMessage")
    val xhtmlMessage: String? = null
)