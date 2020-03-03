package com.valter.openweather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valter.openweather.data.database.entity.forecast.Forecast
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData
import com.valter.openweather.data.repository.OpenWeatherRepository
import com.valter.openweather.dispatchers.DispatchersContainer
import com.valter.openweather.utils.Outcome
import com.valter.openweather.utils.launchSafely

const val CURRENT_WEATHER_CITY = "Lisbon"
const val FORECAST_WEATHER_CITY = "Porto"

class MainViewModel(
        private val repository: OpenWeatherRepository,
        private val dispatchersContainer: DispatchersContainer
) : ViewModel() {

    private var _currentWeatherData = MutableLiveData<Outcome<CurrentWeatherData>>()
    internal var currentWeatherData: LiveData<Outcome<CurrentWeatherData>> = _currentWeatherData

    private var _chartForecasts = MutableLiveData<Outcome<List<Forecast>>>()
    internal var chartForecasts: LiveData<Outcome<List<Forecast>>> = _chartForecasts

    private var _forecasts = MutableLiveData<Outcome<List<Forecast>>>()
    internal var forecasts: LiveData<Outcome<List<Forecast>>> = _forecasts

    init {
        getData()
    }

    private fun getData() {
        getCurrentWeather()
        getChartForecast()
        getForecast()
    }

    private fun getCurrentWeather() {
        viewModelScope.launchSafely(
                _currentWeatherData,
                loading = true,
                context = dispatchersContainer.IO
        ) {
            repository.getCurrentWeather(CURRENT_WEATHER_CITY)
        }
    }

    private fun getChartForecast() {
        viewModelScope.launchSafely(
                _chartForecasts,
                loading = true,
                context = dispatchersContainer.IO
        ) {
            repository.getForecast(CURRENT_WEATHER_CITY)
        }
    }

    private fun getForecast() {
        viewModelScope.launchSafely(
                _forecasts,
                loading = true,
                context = dispatchersContainer.IO
        ) {
            repository.getForecast(FORECAST_WEATHER_CITY)
        }
    }

    fun retry() {
        getData()
    }
}
