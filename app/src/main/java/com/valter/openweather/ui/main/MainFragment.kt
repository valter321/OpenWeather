package com.valter.openweather.ui.main

import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.valter.openweather.R
import com.valter.openweather.data.database.entity.forecast.Forecast
import com.valter.openweather.data.database.entity.weather.CurrentWeatherData
import com.valter.openweather.data.model.ErrorData
import com.valter.openweather.ui.components.BaseFragment
import com.valter.openweather.ui.components.ForecastView
import com.valter.openweather.utils.Outcome
import com.valter.openweather.utils.buildIconUrl
import com.valter.openweather.utils.getWeatherImage
import kotlinx.android.synthetic.main.main_fragment.*
import lecho.lib.hellocharts.model.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.main_fragment

    private val viewModel: MainViewModel by viewModel { parametersOf(this) }

    private lateinit var forecastViewList : MutableList<ForecastView>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.retry()

        viewModel.currentWeatherData.observe(viewLifecycleOwner, Observer { outcome ->
            when (outcome) {
                is Outcome.Progress -> showLoading()
                is Outcome.Success -> setCurrentWeatherData(outcome.data)
                is Outcome.Failure -> showError(
                        ErrorData(R.string.error_message,
                                R.string.retry
                        ),
                        ::onRetryClicked
                )
            }
        })

        viewModel.chartForecasts.observe(viewLifecycleOwner, Observer { outcome ->
            when (outcome) {
                is Outcome.Progress -> showLoading()
                is Outcome.Success -> setChartForecast(outcome.data)
                is Outcome.Failure -> showError(
                        ErrorData(R.string.error_message,
                                R.string.retry
                        ),
                        ::onRetryClicked
                )
            }
        })

        viewModel.forecasts.observe(viewLifecycleOwner, Observer { outcome ->
            when (outcome) {
                is Outcome.Progress -> showLoading()
                is Outcome.Success -> setForecast(outcome.data)
                is Outcome.Failure -> showError(
                        ErrorData(R.string.error_message,
                                R.string.retry
                        ),
                        ::onRetryClicked
                )
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewList = mutableListOf(vwForecastOne, vwForecastTwo,
                vwForecastThree, vwForecastFour, vwForecastFive)
    }

    private fun setCurrentWeatherData(currentWeatherData: CurrentWeatherData) {
        showContent()
        with(currentWeatherData) {
            txtTemperature.text = getString(R.string.temperature, main.temp.toInt())
            Glide.with(context!!)
                    .load(weather[0].icon.buildIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgIcon)
            Glide.with(context!!)
                    .load(weather[0].icon.getWeatherImage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgWeather)
        }
    }

    private fun setChartForecast(data: List<Forecast>) {
        showContent()
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val currentDayForecast = mutableListOf<Forecast>()
        data.forEach { forecast ->
            forecast.date?.let {
                if(it.contains(date))
                    currentDayForecast.add(forecast)
            }
        }
        createDotChart(currentDayForecast)
    }

    private fun createDotChart(list: List<Forecast>) {

        val yAxisData = mutableListOf(22.5, 20.0, 17.5, 15.0, 12.5)

        val values: MutableList<PointValue> = ArrayList()
        list.forEach { forecast ->
            val hour = forecast.date?.substring(11, 13)
            forecast.main.temp?.let {
                values.add(PointValue(hour!!.toFloat(), it.toFloat()))
            }
        }

//        values.add(PointValue(0f, 2f))
//        values.add(PointValue(1f, 4f))
//        values.add(PointValue(2f, 3f))
//        values.add(PointValue(3f, 4f))

        val yAxisValues = mutableListOf<AxisValue>()

        for (i in 0 until yAxisData.size) {
            yAxisValues.add(i, AxisValue(i.toFloat()).setLabel(yAxisData[i].toString()))
        }

        val line = Line(values).apply {
            color = ContextCompat.getColor(context!!, R.color.blue)
            isCubic = true
        }

        val lines = mutableListOf<Line>()
        lines.add(line)

        val data = LineChartData()
        data.lines = lines

        chart.lineChartData = data

        val yAxis = Axis()
        yAxis.values = yAxisValues
        data.axisYRight = yAxis
    }

    private fun setForecast(data: List<Forecast>) {
        val calendar = Calendar.getInstance()

        for (i in 0 until forecastViewList.size) {
            val currentDayForecast = mutableListOf<Forecast>()
            val dayIncrement = if (i == 0) 0 else 1
            calendar.add(Calendar.DAY_OF_YEAR, dayIncrement)
            val date = SimpleDateFormat("EEEE, MMM dd", Locale.getDefault()).format(calendar.time)
            val day = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            data.forEach { forecast ->
                forecast.date?.let {
                    if(it.contains(day)) {
                        currentDayForecast.add(forecast)
                    }
                }
            }

            forecastViewList[i].setData(date, currentDayForecast.toMutableList())
            currentDayForecast.clear()

        }
    }

    private fun onRetryClicked() {
        viewModel.retry()
    }

    override fun onApplyWindowInsets(p0: View?, insets: WindowInsets): WindowInsets {
        return insets
    }

    override fun onDestroyView() {
        forecastViewList.forEach { it.baseAdapter = null }
        super.onDestroyView()
    }
}
