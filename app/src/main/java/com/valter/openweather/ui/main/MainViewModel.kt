package com.valter.openweather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valter.openweather.data.database.entity.forecast.Forecast
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData
import com.valter.openweather.data.model.WeatherData
import com.valter.openweather.data.repository.OpenWeatherRepository
import com.valter.openweather.dispatchers.DispatchersContainer
import com.valter.openweather.utils.Outcome
import com.valter.openweather.utils.launchSafely

class MainViewModel(
        private val repository: OpenWeatherRepository,
        dispatchersContainer: DispatchersContainer
) : ViewModel() {

    private var _currentWeatherData = MutableLiveData<Outcome<CurrentWeatherData>>()
    internal var currentWeatherData: LiveData<Outcome<CurrentWeatherData>> = _currentWeatherData

    private var _forecasts = MutableLiveData<Outcome<List<Forecast>>>()
    internal var forecasts: LiveData<Outcome<List<Forecast>>> = _forecasts

    init {
        viewModelScope.launchSafely(
                _currentWeatherData,
                loading = true,
                context = dispatchersContainer.IO
        ) {
            repository.getCurrentWeather()
        }

        viewModelScope.launchSafely(
                _forecasts,
                loading = true,
                context = dispatchersContainer.IO
        ) {
            repository.getForecast()
        }
    }
}
