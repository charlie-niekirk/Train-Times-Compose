package me.cniekirk.traintimes.data.remote.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Station(
    @Json(name = "crsCode")
    val crsCode: String,
    @Json(name = "stationName")
    val stationName: String
): Parcelable