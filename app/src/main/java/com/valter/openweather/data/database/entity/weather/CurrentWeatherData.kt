package com.valter.openweather.data.database.entity.weather


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import com.valter.openweather.data.database.entity.Weather

const val CURRENT_WEATHER_ID = 1

@JsonClass(generateAdapter = true)
@Entity(tableName = "current_weather")
data class CurrentWeatherData(
        val id: Int,
        @Embedded(prefix = "data_")
        val main: Data,
        val name: String,
        val timezone: Int,
        val weather: List<Weather>
) {
        @PrimaryKey(autoGenerate = false)
        var currentWeatherId = CURRENT_WEATHER_ID
}