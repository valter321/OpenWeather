package com.valter.openweather.data.database.entity.forecast


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.valter.openweather.data.database.entity.Weather

@Entity(tableName = "forecast_weather")
@JsonClass(generateAdapter = true)
data class Forecast(
        @Embedded
        val main: ForecastData?,
        val weather: List<Weather>,
        @Json(name = "dt_txt")
        val date: String?
) {
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null
}