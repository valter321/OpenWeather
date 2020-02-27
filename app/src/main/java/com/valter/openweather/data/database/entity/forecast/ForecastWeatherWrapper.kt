package com.valter.openweather.data.database.entity.forecast

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastWeatherWrapper(
        val list: List<Forecast>
)