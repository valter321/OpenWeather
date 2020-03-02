package com.valter.openweather.data.database.entity.forecast


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastData(
    @Json(name = "feels_like")
    val feelsLike: Double?,
    val humidity: Int?,
    val pressure: Int?,
    val temp: Double?,
    @Json(name = "temp_kf")
    val tempKf: Double?,
    @Json(name = "temp_max")
    val tempMax: Double?,
    @Json(name = "temp_min")
    val tempMin: Double?
)