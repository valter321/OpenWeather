package com.valter.openweather.network

import com.valter.openweather.data.database.entity.forecast.ForecastWeatherWrapper
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("weather")
    suspend fun fetchCurrentWeather(
            @Query("q") city: String,
            @Query("units") units: String
    ) : CurrentWeatherData

    @GET("forecast")
    suspend fun fetchForecast(
            @Query("q") city: String,
            @Query("units") units: String
    ) : ForecastWeatherWrapper
}