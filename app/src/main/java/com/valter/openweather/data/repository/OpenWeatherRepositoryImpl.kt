package com.valter.openweather.data.repository

import android.content.Context
import com.valter.openweather.data.database.CurrentWeatherDao
import com.valter.openweather.data.database.ForecastWeatherDao
import com.valter.openweather.data.database.entity.forecast.Forecast
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData
import com.valter.openweather.network.OpenWeatherService
import com.valter.openweather.utils.isConnectedToNetwork

class OpenWeatherRepositoryImpl(
        private val currentWeatherDao: CurrentWeatherDao,
        private val forecastWeatherDao: ForecastWeatherDao,
        private val openWeatherService: OpenWeatherService,
        context: Context
) : OpenWeatherRepository {

    private val appContext = context.applicationContext

    override suspend fun getCurrentWeather() = if (appContext.isConnectedToNetwork()) {
        fetchCurrentWeatherFromNetwork()
    } else {
        fetchCurrentWeatherFromDatabase()
    }

    override suspend fun getForecast(city: String) = if (appContext.isConnectedToNetwork()) {
        fetchForecastFromNetwork(city)
    } else {
        fetchForecastWeatherFromDatabase()
    }

    private suspend fun fetchCurrentWeatherFromNetwork() = openWeatherService.fetchCurrentWeather("Lisbon", "metric").also {
        persistCurrentWeather(it)
    }

    private suspend fun fetchForecastFromNetwork(city: String) = openWeatherService.fetchForecast(city, "metric").list.also {
        persistForecastWeather(it)
    }

    private suspend fun fetchCurrentWeatherFromDatabase() = currentWeatherDao.getCurrentWeather()

    private suspend fun fetchForecastWeatherFromDatabase() = forecastWeatherDao.getForecast()

    private suspend fun persistCurrentWeather(currentWeatherData: CurrentWeatherData) = currentWeatherDao.insert(currentWeatherData)

    private suspend fun persistForecastWeather(forecast: List<Forecast>) = forecastWeatherDao.insert(forecast)
}