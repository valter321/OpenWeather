package com.valter.openweather.data.model

import com.valter.openweather.data.database.entity.forecast.Forecast
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData

data class WeatherData(
        private val currentWeatherData: CurrentWeatherData,
        private val forecasts: List<Forecast>
)