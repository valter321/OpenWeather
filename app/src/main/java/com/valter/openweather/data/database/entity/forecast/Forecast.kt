package com.valter.openweather.data.database.entity.forecast


import androidx.room.Embedded
import androidx.room.Entity
import com.squareup.moshi.JsonClass
import com.valter.openweather.data.database.entity.Weather

@Entity
@JsonClass(generateAdapter = true)
data class Forecast(
        @Embedded
        val main: ForecastData,
        val weather: List<Weather>
)