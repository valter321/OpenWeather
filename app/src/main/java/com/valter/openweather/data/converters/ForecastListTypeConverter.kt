package com.valter.openweather.data.converters

import com.valter.openweather.data.database.entity.forecast.Forecast

class ForecastListTypeConverter :
        ListTypeConverter<Forecast>(Forecast::class.java)