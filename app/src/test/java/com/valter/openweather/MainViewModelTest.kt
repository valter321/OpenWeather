package com.valter.openweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.valter.openweather.data.database.entity.forecast.Forecast
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData
import com.valter.openweather.data.repository.OpenWeatherRepository
import com.valter.openweather.dispatchers.TestDispatcherContainer
import com.valter.openweather.ui.main.CURRENT_WEATHER_CITY
import com.valter.openweather.ui.main.FORECAST_WEATHER_CITY
import com.valter.openweather.ui.main.MainViewModel
import com.valter.openweather.utils.Outcome
import com.valter.openweather.utils.blockingObserve
import com.valter.openweather.utils.insideValue
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this)
    }

    @Test
    fun `Loading weather data, should broadcast the current weather, the current weather city forecast and the 5 day forecast for Porto`() {
        val currentWeatherData = mockk<CurrentWeatherData>(relaxed = true)
        val forecastData = (1..10).map { mockk<Forecast>(relaxed = true) }

        val repository: OpenWeatherRepository = mockk {
            io.mockk.coEvery { getCurrentWeather(CURRENT_WEATHER_CITY) } returns currentWeatherData
            io.mockk.coEvery { getForecast(CURRENT_WEATHER_CITY) } returns forecastData
            io.mockk.coEvery { getForecast(FORECAST_WEATHER_CITY) } returns forecastData
        }

        val viewModel = MainViewModel(repository, TestDispatcherContainer)
        viewModel.currentWeatherData.blockingObserve()
        viewModel.chartForecasts.blockingObserve()
        viewModel.forecasts.blockingObserve()

        with(viewModel.currentWeatherData) {
            assert(value is Outcome.Success)
            assert(value.insideValue == currentWeatherData)
        }

        with(viewModel.chartForecasts) {
            assert(value is Outcome.Success)
            assert(value.insideValue == forecastData)
        }

        with(viewModel.forecasts) {
            assert(value is Outcome.Success)
            assert(value.insideValue == forecastData)
        }
    }
}