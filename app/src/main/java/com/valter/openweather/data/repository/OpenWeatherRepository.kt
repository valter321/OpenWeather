package com.valter.openweather.data.repository

import com.valter.openweather.data.database.entity.forecast.Forecast
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData

interface OpenWeatherRepository {

    suspend fun getCurrentWeather(city: String) : CurrentWeatherData
    suspend fun getCurrentForecast(city :String) : List<Forecast>
    suspend fun getForecast(city :String) : List<Forecast>
}